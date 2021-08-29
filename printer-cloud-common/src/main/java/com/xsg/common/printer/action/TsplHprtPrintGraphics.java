package com.xsg.common.printer.action;

import com.xsg.common.printer.constants.BaseConstants;
import com.xsg.common.printer.model.FeedLine;
import com.xsg.common.printer.model.FontDrawSize;
import com.xsg.common.printer.model.XsgFont;
import com.xsg.common.printer.utils.DrawUtil;
import com.xsg.common.printer.utils.ImageUtil;
import lombok.Data;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * @author 高总辉
 * @desc
 * @date 2020/12/21 4:18 下午
 */
@Data
public class TsplHprtPrintGraphics {

    /**
     * 指令集
     */
    public String command;

    /**
     * 宽度
     */
    public final int width;

    /**
     * 高度
     */
    public final int height;

    /**
     * 上下纸张间距
     */
    public final int gap;

    public TsplHprtPrintGraphics(int width, int height, int gap) {
        this.width = width;
        this.height = height;
        this.gap = gap;
        init(width, height, gap);
    }

    public void init (int width, int height, int gap) {
        addCommand(String.format(TsplPrinterGraphics.TPrinterCommand.CLS));
        addCommand(String.format(TsplPrinterGraphics.TPrinterCommand.SIZE, width/8, height/8));
        addCommand(String.format(TsplPrinterGraphics.TPrinterCommand.GAP, gap, 0));
        addCommand(String.format(TsplPrinterGraphics.TPrinterCommand.DIRECTION, 1));
        addCommand(String.format(TsplPrinterGraphics.TPrinterCommand.REFERENCE, 30, 30));
        addCommand(String.format(THPrinterCommand.SHIFT, -36));
        addCommand(String.format(TsplPrinterGraphics.TPrinterCommand.CLS));
    }

    public void drawLine(int x1, int y1, int x2, int y2, int borderType, int color) {
        int width = x2 - x1;
        int height = y2 - y1;
        if (width > 0) {
            addCommand(String.format(TsplPrinterGraphics.TPrinterCommand.BAR, x1, y1, width, 1));
        }
        if (height > 0) {
            addCommand(String.format(TsplPrinterGraphics.TPrinterCommand.BAR, x1, y1, 1, height));
        }
    }

    public void drawQrCode(String value, int x, int y, int width, int height, boolean isCompress) {
        addCommand(String.format(TsplPrinterGraphics.TPrinterCommand.QRCODE, x, y, "L", 6, "A", 0,value ));
    }

    public void drawBarCode(String value, int x, int y, int width, int height, boolean isCompress) {
        addCommand(String.format(TsplPrinterGraphics.TPrinterCommand.BARCODE, x, y, "39", height, 1,0,2,4,value));
    }

    public void drawImage(String path, int x, int y, int width, int height, boolean isCompress) {
        String content =ImageUtil.base64(path);
        addCommand(String.format(THPrinterCommand.IMAGE, x, y, height - 10, width - 10, 0, content));
    }

    public int drawString(String str, int x, int y, int x0, int y0, XsgFont font) {
        int width = x0 - x;
        int height = y0 - y;

        FontDrawSize fontDrawSize = BaseConstants.getTsplHrptFontDrawSize(font.getFontSizeEnum());
        int size = Integer.valueOf(fontDrawSize.getFontNameOrSize());
        if (font.isBold()) {
            addCommand(String.format(THPrinterCommand.BOLD, 1));
        }
        if (font.isNeedFeedLine()) {
            List<FeedLine> feedLines = DrawUtil.feedLine(height, width, str, fontDrawSize.getChineseHeight(), fontDrawSize.getEnglishHeight(), fontDrawSize.getChineseWidth(), fontDrawSize.getEnglishWidth());
            for (FeedLine feedLine : feedLines) {
                addCommand(String.format(TsplPrinterGraphics.TPrinterCommand.TEXT, x, y, "9", 0, size, size, feedLine.getLine()));
                y = y + feedLine.getHeight();
            }
        } else {
            addCommand(String.format(TsplPrinterGraphics.TPrinterCommand.TEXT, x, y, "9", 0, size, size, str));
            if (font.isFit()) {
                int strWidth = fontDrawSize.getTotalWidth(str);
//                if (strWidth > width) {
                    return strWidth - width;
//                }
            }
        }
        if (font.isBold()) {
            addCommand(String.format(THPrinterCommand.BOLD, 0));
        }
        return 0;
    }

    public void end () {
        addCommand(String.format(TsplPrinterGraphics.TPrinterCommand.PRINT, 1, 1));
        command = command.replaceAll("\\{", "").replaceAll("\\}", "") + "\r\n";
    }

    /**
     *
     * @param command
     */
    private void addCommand (String command) {
        if (StringUtils.isBlank(command)) {
            return;
        }
        if (this.command != null) {
            this.command = this.command + "\r\n" +command;
        } else {
            this.command = command;
        }
    }

    public static class THPrinterCommand {

        /**
         * n
         * 该指令用于将文字加粗
         * 0：关闭
         * 1：开启
         */
        public static final String BOLD = "BOLD {%s}";

        /**
         * 绘制圆
         * X_start x起点 单位dots
         * Y_start y起点 单位dots
         * diameter 直径 单位dots
         * thickness 厚度 单位dots
         */
        public static final String CIRCLE = "CIRCLE {%s}, {%s}, {%s}, {%s} {%s}";

        /**
         * 用于移动的垂直位置
         * n: -90 <= n <= 90 dots
         */
        public static final String SHIFT = "SHIFT {%s}";

        /**
         * x 位图左上角 X 坐标
         * y 位图左上角 Y 坐标
         * width 位图的宽度，单位 byte
         * height 位图的高度，单位 dot
         * mode 位图绘制模式
         *      0 OVERWRITE
         *      1 OR
         *      2 XOR
         * bitmap data 16 进制图像数据
         * 语句：BITMAP x,y,width,height,mode,bitmap data
         * 例子：
         * SIZE 4,2
         * GAP 0,0
         * CLS
         * BITMAP 200,200,2,16,0,data
         * PRINT 1,1
         */
        public static final String IMAGE = "<Bitmap x={%s} y={%s} h={%s} w={%s} m={%s}>{%s}</Bitmap>";

    }
}
