package com.xsg.common.printer.utils;

import com.xsg.common.printer.constants.VoucherConstants;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;


/**
 * @author 高总辉
 * @desc
 * @date 2020/11/24 11:28 上午
 */
public class ImageUtil {

    private static int gapHeight = 15;

    /**
     * 插入图片 自定义图片的宽高
     * @param imgPath
     *             插入图片的路径
     * @param imgWidth
     *             设置图片的宽度
     * @param imgHeight
     *             设置图片的高度
     * @param isCompress
     *             是否按输入的宽高定义图片的尺寸，只有为true时 输入的宽度和高度才起作用<br/>
     *             为false时输入的宽高不起作用，按输入图片的默认尺寸
     * @return
     * @throws Exception
     * */
    public static Image readImage(String imgPath, int imgWidth, int imgHeight, boolean isCompress) throws Exception {
        File file = new File(imgPath);
        Image src = ImageIO.read(file);
        if (isCompress) {
            Image image = src.getScaledInstance(imgWidth, imgHeight, Image.SCALE_SMOOTH);
            return image;
        }
        return src;
    }

    /**
     * 存储图片
     * @param image
     * @param file
     */
    public static void createImage(BufferedImage image, File file) {
        try {
            ImageIO.write(image, "png", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Java拼接多张图片
     * @return
     */
    public static boolean merge(String[] pics, String destPath) {
        try {
            Integer allWidth = 0;
            Integer allHeight = 0;
            java.util.List<BufferedImage> imgs = new ArrayList<>();
            for(int i=0; i< pics.length; i++){
                File file = new File(pics[i]);
                imgs.add(ImageIO.read(file));
                //因为是竖向合成，拿图片里最大的一个宽度就行
                allWidth = Math.max(allWidth,imgs.get(i).getWidth());
                allHeight += imgs.get(i).getHeight();
                if (i != pics.length - 1) {
                    allHeight = allHeight + gapHeight;
                }
            }
            if (imgs == null || imgs.isEmpty()) {
                return false;
            }
            BufferedImage combined = new BufferedImage(allWidth, allHeight,BufferedImage.TYPE_INT_RGB);
            Graphics g = combined.getGraphics();
            //设置画布背景颜色 ，默认白色
            g.setColor(Color.white);
            g.fillRect(0, 0, allWidth, allHeight);
            Integer height = 0;
            Image gapImage = readImage(VoucherConstants.IMAGE_GAP_PATH, imgs.get(0).getWidth(), gapHeight, true);
            for(int i = 0; i < imgs.size(); i++){
                BufferedImage image = imgs.get(i);
                g.drawImage(image, 0, height,null);
                //+10为了设置上下两个图片间距
                height = height + imgs.get(i).getHeight();
                if (i != imgs.size() - 1) {
                    g.drawImage(gapImage, 0, height, null);
                    height = height + gapHeight;
                }
            }
            ImageIO.write(combined, "png", new File(destPath));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     *
     * @param mini 贴图
     * @param Scale 缩放比例
     * @return
     */
    public static BufferedImage modifyImageScale(BufferedImage mini,double Scale) {
        int w = (int)Math.round(mini.getWidth()*Scale);
        int h = (int)Math.round(mini.getHeight()*Scale);
        //设置生成图片宽*高，色彩
        BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        //创建画布
        Graphics2D g2 = bi.createGraphics();
        //设置图片透明  注********：只有png格式的图片才能设置背景透明，jpg设置图片颜色变的乱七八糟
//        bi = g2.getDeviceConfiguration().createCompatibleImage(w, h, Transparency.TRANSLUCENT);
        //重新创建画布
        g2 = bi.createGraphics();
        //画图
        g2.drawImage(mini, 0,0,w,h, null);
        //关闭资源
        g2.dispose();
        return bi;
    }

    /**
     *
     * @param mini 贴图
     * @param ratio  旋转角度
     * @return
     */
    public static BufferedImage modifyImageRatio(BufferedImage mini,int ratio) {
        int src_width = mini.getWidth();
        int src_height = mini.getHeight();
        //针对图片旋转重新计算图的宽*高
        Rectangle rect_des = CalcRotatedSize(new Rectangle(new Dimension(
                src_width, src_height)), ratio);
        //设置生成图片的宽*高，色彩度
        BufferedImage res = new BufferedImage(rect_des.width, rect_des.height,BufferedImage.TYPE_INT_RGB);
        //创建画布
        Graphics2D g2 = res.createGraphics();
        res = g2.getDeviceConfiguration().createCompatibleImage(rect_des.width, rect_des.height, Transparency.TRANSLUCENT);
        g2 = res.createGraphics();
        //重新设定原点坐标
        g2.translate((rect_des.width - src_width) / 2,
                (rect_des.height - src_height) / 2);
        //执行图片旋转，rotate里包含了translate，并还原了原点坐标
        g2.rotate(Math.toRadians(ratio), src_width / 2, src_height / 2);
        g2.drawImage(mini, null, null);
        g2.dispose();
        return res;
    }

    private static Rectangle CalcRotatedSize(Rectangle src, int angel) {
        if (angel >= 90) {
            if(angel / 90 % 2 == 1){
                int temp = src.height;
                src.height = src.width;
                src.width = temp;
            }
            angel = angel % 90;
        }

        double r = Math.sqrt(src.height * src.height + src.width * src.width) / 2;
        double len = 2 * Math.sin(Math.toRadians(angel) / 2) * r;
        double angel_alpha = (Math.PI - Math.toRadians(angel)) / 2;
        double angel_dalta_width = Math.atan((double) src.height / src.width);
        double angel_dalta_height = Math.atan((double) src.width / src.height);

        int len_dalta_width = (int) (len * Math.cos(Math.PI - angel_alpha
                - angel_dalta_width));
        int len_dalta_height = (int) (len * Math.cos(Math.PI - angel_alpha
                - angel_dalta_height));
        int des_width = src.width + len_dalta_width * 2;
        int des_height = src.height + len_dalta_height * 2;
        return new Rectangle(new Dimension(des_width, des_height));
    }


    public static String base16(String imgFile) {
        byte[] data = null;
        try (InputStream in = new FileInputStream(imgFile)) {
            data = new byte[in.available()];
            in.read(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        HexBinaryAdapter hexBinaryAdapter = new HexBinaryAdapter();
        return hexBinaryAdapter.marshal(data);
    }

    public static String base64(String imgFile) {
        byte[] data = null;
        try (InputStream in = new FileInputStream(imgFile)) {
            data = new byte[in.available()];
            in.read(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data != null ? data : new byte[0]);
    }

    public static String base64(BufferedImage bufferedImage) {
        String base64 = "";
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "png", bos);
            byte[] imageBytes = bos.toByteArray();
            BASE64Encoder encoder = new BASE64Encoder();
            base64 = encoder.encode(imageBytes);
            bos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return base64;
    }
}
