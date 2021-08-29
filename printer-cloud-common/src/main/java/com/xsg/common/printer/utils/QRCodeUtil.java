package com.xsg.common.printer.utils;

import com.alibaba.fastjson.JSONObject;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.xsg.common.printer.constants.VoucherConstants;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @Classname ZxingQRCodeUtil
 * @Description Zxing二维码生成工具
 * @Date 2019-08-29 17:53
 */
public class QRCodeUtil {

    private final static Logger log = LoggerFactory.getLogger(QRCodeUtil.class);

    private static final int QRCOLOR = 0xFF000000; // 默认是黑色
    private static final int BGWHITE = 0xFFFFFFFF; // 背景颜色

    private static final int WIDTH = 300; // 二维码宽
    private static final int HEIGHT = 300; // 二维码高

    private static final String basePath = VoucherConstants.PRINT_PATH;

    /**
     * 用于设置QR二维码参数
     */
    private static Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>() {
        {
            // 设置QR二维码的纠错级别（H为最高级别）具体级别信息
            put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
            put(EncodeHintType.CHARACTER_SET, "GBK");
            put(EncodeHintType.MARGIN, 0);
        }
    };

    /**
     * 二维码
     * @param qrData
     * @param width
     * @param height
     * @return
     */
    public static String drawQRCode (String qrData, int width, int height) {
        File file = new File(basePath, PathUtil.getRandomPngName());
        FileWriterUtils.createIfNotExists(file);
        drawLogoQRCode(null, file, qrData, null, width, height);
        return file.getPath();
    }

    /**
     * @param logoFile 图片文件
     * @param codeFile 二维码储存地址
     * @param qrData 二维码内容
     * @param note 二维码说明
     * @return
     */
    public static Boolean drawLogoQRCode(InputStream logoFile, File codeFile, String qrData, String note, int width, int height) {
        try {
            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
            // 参数顺序分别为：编码内容，编码类型，生成图片宽度，生成图片高度，设置参数
            BitMatrix bm = multiFormatWriter.encode(qrData, BarcodeFormat.QR_CODE, width, height, hints);
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

            // 开始利用二维码数据创建Bitmap图片，分别设为黑（0xFFFFFFFF）白（0xFF000000）两色
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    image.setRGB(x, y, bm.get(x, y) ? QRCOLOR : BGWHITE);
                }
            }
            width = image.getWidth();
            height = image.getHeight();
            if (logoFile!=null) {
                // 构建绘图对象
                Graphics2D g = image.createGraphics();
                // 读取Logo图片
                BufferedImage logo = ImageIO.read(logoFile);
                // 开始绘制logo图片
                g.drawImage(logo, width * 2 / 5, height * 2 / 5, width * 2 / 10, height * 2 / 10, null);
                g.dispose();
                logo.flush();
            }

            // 自定义文本描述
            if (!StringUtils.isEmpty(note)) {
                // 新的图片，把带logo的二维码下面加上文字
                BufferedImage outImage = new BufferedImage(width, height + 25, BufferedImage.TYPE_4BYTE_ABGR);
                Graphics2D outg = outImage.createGraphics();
                // 画二维码到新的面板
                outg.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
                // 画文字到新的面板
                outg.setColor(Color.BLACK);
                // 字体、字型、字号
                outg.setFont(new Font(null,Font.BOLD, 28));
                int strWidth = outg.getFontMetrics().stringWidth(note);
                if (strWidth > 299) {
                    // //长度过长就截取前面部分
                    // 长度过长就换行
                    String note1 = note.substring(0, note.length() / 2);
                    String note2 = note.substring(note.length() / 2, note.length());
                    int strWidth1 = outg.getFontMetrics().stringWidth(note1);
                    int strWidth2 = outg.getFontMetrics().stringWidth(note2);
                    outg.drawString(note1, 155 - strWidth1 / 2, height + (outImage.getHeight() - height) / 2 + 12);
                    BufferedImage outImage2 = new BufferedImage(width, height + 25, BufferedImage.TYPE_4BYTE_ABGR);
                    Graphics2D outg2 = outImage2.createGraphics();
                    outg2.drawImage(outImage, 0, 0, outImage.getWidth(), outImage.getHeight(), null);
                    outg2.setColor(Color.BLACK);
                    // 字体、字型、字号
                    outg2.setFont(new Font(null, Font.BOLD, 28));
                    outg2.drawString(note2, 155 - strWidth2 / 2,outImage.getHeight() + (outImage2.getHeight() - outImage.getHeight()) / 2 + 5);
                    outg2.dispose();
                    outImage2.flush();
                    outImage = outImage2;
                } else {
                    //画文字
                    outg.drawString(note, 155 - strWidth / 2, height + (outImage.getHeight() - height) / 2 + 12);
                }
                outg.dispose();
                outImage.flush();
                image = outImage;
            }
            image.flush();
            ImageIO.write(image, "png", codeFile);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("生成二维码失败，失败原因：{}", e.getMessage(), e);
            return false;
        }
    }

    public static String getQrData(String qrUrl,String qrParam){
        if(StringUtils.isEmpty(qrParam)){
            qrParam = "{}";
        }
        try{
            JSONObject json = JSONObject.parseObject(qrParam);
            Iterator iterator = json.keySet().iterator();
            StringBuffer sb = new StringBuffer();
            sb.append(qrUrl).append("?");
            while(iterator.hasNext()){
                String key = iterator.next().toString();
                String value = URLEncoder.encode(json.getString(key), "utf-8");
                sb.append(key).append("=").append(value).append("&");
            }
            return sb.substring(0,sb.length()-1);
        }catch (Exception e){
            log.error("封装二维码内容错误，错误原因：{}", e.getMessage());
            return "";
        }
    }
}
