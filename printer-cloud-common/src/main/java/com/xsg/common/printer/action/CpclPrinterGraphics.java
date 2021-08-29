package com.xsg.common.printer.action;

import com.xsg.common.printer.constants.FontSizeEnum;
import com.xsg.common.printer.constants.BaseConstants;
import com.xsg.common.printer.model.FeedLine;
import com.xsg.common.printer.model.FontDrawSize;
import com.xsg.common.printer.model.XsgFont;
import com.xsg.common.printer.utils.DrawUtil;
import org.apache.commons.lang.StringUtils;

import java.util.List;
import java.util.Objects;

/**
 * @author 高总辉
 * @desc
 * @date 2020/11/27 1:57 下午
 */
public class CpclPrinterGraphics {

    /**
     * 命令行
     */
    private String command;

    public final int width;

    public final int height;

    public CpclPrinterGraphics(int width, int height) {
        this.width = width;
        this.height = height;
        init(width, height);
    }

    public String getCommand() {
        return command;
    }

    /**
     * 画线
     * @param
     */
    public void drawLine(int x1, int y1, int x2, int y2, int borderType, int color) {
        addCommand(GPrinterCommand.getLINE(x1, y1, x2, y2, 2));
    }

    /**
     * 画二维码
     */
    public void drawQrCode (String value, int x, int y, int width, int height, boolean isCompress) {
        addCommand(GPrinterCommand.getQRCODE(BarcodeEnum.BARCODE, x, y, 6, value));
    }

    /**
     * 画条形码
     */
    public void drawBarCode (String value, int x, int y, int width, int height, boolean isCompress) {
        addCommand(GPrinterCommand.getBarcodeTextStart(7, 0, 5));
        addCommand(GPrinterCommand.getBARCODE(128, 2,1, height, x, y, value));
        addCommand(GPrinterCommand.getBarcodeTextEnd());
    }

    /**
     * 画图片
     * @param
     */
    public void drawImage (String path, int x, int y, int width, int height, boolean isCompress) {
        //todo ...
        String imageText = DrawUtil.getSpecialStr(path);
        if (imageText != null) {
            FontDrawSize fontDrawSize = BaseConstants.getCpclFontDrawSize(FontSizeEnum.BIG);
            addCommand(GPrinterCommand.getSETBOLD(2));
            addCommand(GPrinterCommand.getSETMAG(2, 2));
            addCommand(GPrinterCommand.getTEXT(TextEnum.TEXT, Integer.valueOf(fontDrawSize.getFontNameOrSize()), 0, x, y, imageText));
            addCommand(GPrinterCommand.getSETMAG(0, 0));
            addCommand(GPrinterCommand.getSETBOLD(0));
        }
    }

    /**
     * 画文字
     * @param
     */
    public int drawString (String str, int x, int y, int x0, int y0, XsgFont font) {
        int width = x0 - x;
        int height = y0 - y;
        if (font.isBold()) {
            addCommand(GPrinterCommand.getSETBOLD(2));
        }
        if (FontSizeEnum.BIG.equals(font.getFontSizeEnum())) {
            addCommand(GPrinterCommand.getSETMAG(2, 2));
        }
        int fitWidth = 0;
        FontDrawSize fontDrawSize = BaseConstants.getCpclFontDrawSize(font.getFontSizeEnum());
        if (font.isNeedFeedLine()) {
            List<FeedLine> feedLines = DrawUtil.feedLine(height, width, str, fontDrawSize.getChineseHeight(), fontDrawSize.getEnglishHeight(), fontDrawSize.getChineseWidth(), fontDrawSize.getEnglishWidth());
            for (FeedLine feedLine : feedLines) {
                addCommand(GPrinterCommand.getTEXT(TextEnum.TEXT, Integer.valueOf(fontDrawSize.getFontNameOrSize()), 0, x, y, feedLine.getLine()));
                y = y + feedLine.getHeight();
            }
        } else {
            addCommand(GPrinterCommand.getTEXT(TextEnum.TEXT, Integer.valueOf(fontDrawSize.getFontNameOrSize()), 0, x, y, str));
            if (font.isFit()) {
                int strWidth = fontDrawSize.getTotalWidth(str);
//                if (strWidth > width) {
                    fitWidth =  strWidth - width;
//                }
            }
        }
        if (FontSizeEnum.BIG.equals(font.getFontSizeEnum())) {
            addCommand(GPrinterCommand.getSETMAG(0, 0));
        }
        if (font.isBold()) {
            addCommand(GPrinterCommand.getSETBOLD(0));
        }
        return fitWidth;
    }

    /**
     * 结束打印
     */
    public void end () {
        addCommand(GPrinterCommand.getFORM());
        addCommand(GPrinterCommand.getEND());
        command = command.replaceAll("\\{", "").replaceAll("\\}", "") + "\n";
    }

    /**
     * 初始化面单长度，高度
     * @param width
     * @param height
     */
    private void init (int width, int height) {
        addCommand(GPrinterCommand.getSTART(0, height, 1));
        addCommand(GPrinterCommand.getPageWidth(width));
        addCommand(GPrinterCommand.getSENSE());
        addCommand("SET-TOF 0");
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
            this.command = this.command + "\n" +command;
        } else {
            this.command = command;
        }
    }

    public static class GPrinterCommand {
        /**
         * 开始命令行
         */
        protected static final String START = "! {%s} 200 200 {%s} {%s}";

        /**
         * 结束命令行
         */
        protected static final String END = "PRINT";

        /**
         * POPRINT 命令：旋转 180 度后，执行 PRINT 命令内容
         */
        protected static final String POPRINT = "POPRINT";

        /**
         * FORM 命令可以指示打印机在一页打印结束后切换至下一页顶部。
         */
        protected static final String FORM = "FORM";

        /**
         * ABORT 命令可以在不打印的情况下终止当前的控制会话。
         */
        protected static final String ABORT = "ABORT";

        /**
         * PREFEED 命令指示打印机在打印之前将介质向前移动指定长度
         */
        protected static final String PREFEED = "PREFEED {length}";

        /**
         * SETMAG 命令可将常驻字体放大指定的放大倍数。
         * width: 字体的宽度放大倍数。有效放大倍数为 1 到 10。
         * height: 字体的宽度放大倍数。有效放大倍数为 1 到 10。
         * 后面不需要时， 需要重置
         */
        protected static final String SETMAG = "SETMAG {%s} {%s}";

        /**
         * CONTRAST 命令用于指定整个标签的打印黑度。最亮的打印输出为对比度级别 0。最暗 的对比度级别为 3。
         * 0 = 默认值 1 = 中 2 = 暗 3 = 非常暗
         */
        protected static final String CONTRAST = "CONTRAST {level}";

        protected static final String SPEED = "SPEED {%s}";

        /**
         * UNDERLINE 命令用于给文本加下划线。仅当所使用的字体支持下划线时，此命令才会起 作用。如果所使用的字体不支持 UNDERLINE，则将忽略此命令。
         * mode: "on","off"
         */
        protected static final String UNDERLINE = "UNDERLINE {mode}";

        /**
         * SETBOLD 命令可使文本加粗并且稍微加宽。SETBOLD 命令会采用一个操作数来设置文本变 黑的程度。
         * {value} 是介于 0 到 5 之间的偏移量
         * 默认单位设置以点为单位。（203 点 = 1 英寸） 如果单位为英寸，则偏移值的范围为 0-0.0246 英寸。 如果单位为厘米，则偏移值的范围为 0-0.0625 厘米。 如果单位为毫米，则偏移值的范围为 0-0.625 毫米。
         * 完成后，请务必发出“!U1 SETBOLD 0”命令以禁用粗体格式。
         */
        protected static final String SETBOLD = "SETBOLD {%s}";

        protected static final String TEXT = "{%s} {%s} {%s} {%s} {%s} {%s}";

        protected static final String LINE = "LINE {%s} {%s} {%s} {%s} {%s}";

        /**
         * INVERSE-LINE 命令的语法与 LINE 命令相同。位于 INVERSE-LINE 命令所定义区域内 的以前创建的对象的黑色区域将重绘为白色，白色区域将重绘为黑色。
         * {command}： 从下面选择一项： LINE（或 L）：打印线条。
         * {x1}：左上角的 X 坐标。
         * {y1}：左上角的 Y 坐标。
         * {x2}：以下项的 X 坐标： - 水平轴的右上角。 - 垂直轴的左下角。
         * {y2}：以下项的 Y 坐标： - 水平轴的右上角。 - 垂直轴的左下角。
         * {width}：线条的单位宽度
         */
        protected static final String INVERSE_LINE = "INVERSE-LINE {x1} {y1} {x2} {y2} {width}";

        /**
         * 用户可以使用 BOX 命令生成具有指定线条宽度的矩形。
         * {command}：BOX
         * {x 0 }：左上角的 X 坐标。
         * {y 0 }：左上角的 Y 坐标。
         * {x 1 }：右下角的 X 坐标。
         * {y 1 }：右下角的 Y 坐标。
         * {width}：形成矩形框的线条的单位宽度。
         */
        protected static final String BOX = "BOX {x1} {y1} {x2} {y2} {width}";

        /**
         * TONE 命令可用于替代 CONTRAST 命令来指定所有标签的打印黑度。最亮的打印输出为 色调级别 -99。最暗的色调级别为 200。
         * 打印机在开机时的默认色调级别为 0。色调级别设置 在更改前对所有打印任务保持有效。TONE 和 CONTRAST 命令不能彼此组合使用。
         * {level}： 选择介于 -99 到 200 之间的值。 对比度级别和色调级别的等价等效关系：
         * 对比度 0 = 色调 0 对比度 1 = 色调 100 对比度 2 = 色调 200 对比度 3 = 无等效色调
         */
        protected static final String TONE = "TONE {level}";

        /**
         * 打印机假定页面宽度为打印机的完整宽度。打印会话的最大高度由页面宽度和可用打印内 存决定。
         * 如果页面宽度小于打印机的完整宽度，则用户可以通过指定页面宽度来增加最大页面 高度
         * {width}：页面的单位宽度。
         */
        protected static final String PAGE_WIDTH = "PAGE-WIDTH {%s}";

        protected static final String BARCODE_TEXT_START = "BARCODE-TEXT {%s} {%s} {%s}";

        /**
         * 条码结束的必要
         */
        protected static final String BARCODE_TEXT_END = "BARCODE-TEXT OFF";

        /**
         * 命令能够以指定的宽度和高度纵向和横向打印条码。
         * {command}：从下面选择一项： BARCODE（或 B）：横向打印条码。 VBARCODE（或 VB） 纵向打印条码。
         * {type}：从下表中选择：
         * UPCA、UPCA2、UPCA5
         * UPCE、UPCE2、UPCE5
         * EAN13、EAN132、EAN135
         * EAN8、EAN82、EAN 85
         * 39、39C、F39、F39C
         * 93
         * I2OF5
         * I2OF5C
         * I2OF5G
         * 128
         * UCCEAN128
         * CODABAR、CODABAR16
         * MSI、MSI10、MSI1010、MSI1110
         * POSTNET
         * FIM
         */
        protected static final String BARCODE = "BARCODE {%s} {%s} {%s} {%s} {%s} {%s} {%s}";

        protected static final String QRCODE = "{%s} {%s} {%s} {%s} M {%s} U {%s}\n"
                                             + "MA,{%s}\n"
                                             + "ENDQR";

        /**
         * 此命令用于指示打印机让蜂鸣器发出给定时间长度的声音。未配备蜂鸣器的打印机将忽略 此命令。
         * {beep _ length}：蜂鸣持续时间，以 1/8 秒为单位递增指定。
         * 此示例指示打印机蜂鸣两秒钟（16 x .125 秒 = 2 秒）
         */
        protected static final String BEEP = "BEEP {beep_length}";

        /**
         * 必要时，可以使用 JOURNAL 命令禁用自动校正功能。 用户程序负责在 JOURNAL 模式下进行检查并确保有纸
         */
        protected static final String JOURNAL = "JOURNAL";

        /**
         * 指示打印机应采用的页顶检测方式
         * GAP-SENSE # (0-255) BAR-SENSE # (0-255)
         */
        protected static final String SENSE = "GAP-SENSE";

        /**
         * 可以使用图形命令打印位映射图形
         * {command}：从下面选择一项： EXPANDED-GRAPHICS（或 EG）：横向打印扩展图形。 VEXPANDED-GRAPHICS（或 VEG）：纵向打印扩展图形。 COMPRESSED-GRAPHICS（或 CG）：横向打印压缩图形。 VCOMPRESSED-GRAPHICS（或 VCG）：纵向打印压缩图形。
         * {width}：图像的宽度（以字节为单位）。
         * {height}：图像的高度（以点为单位）。
         * {x}：横向起始位置。
         * {y}：纵向起始位置。
         * {data}：图形数据。
         */
        protected static final String GRAPHICS = "{command} {width} {height} {x} {y} {data}";

        /**
         * 开始命令行
         */
        public static String getSTART(Integer offset, Integer height, Integer qty) {
            Objects.requireNonNull(offset, "start offset can not null");
            Objects.requireNonNull(height, "start height can not null");
            Objects.requireNonNull(qty, "start qty can not null");
            return String.format(START, offset, height, qty);
        }

        public static String getEND() {
            return END;
        }

        public static String getPOPRINT() {
            return POPRINT;
        }

        public static String getFORM() {
            return FORM;
        }

        public static String getABORT() {
            return ABORT;
        }

        public static String getPREFEED() {
            return PREFEED;
        }

        public static String getSETMAG(int height, int width) {
            return String.format(SETMAG, width, height);
        }

        public static String getCONTRAST() {
            return CONTRAST;
        }

        /**
         * 此命令用于设置电机的最高速度级别。每一款打印机型号都设置了最低和最高极限速度。
         * SPEED 命令可以在 0 到 5 的范围内选择速度级别，0 表示最低速度。为每一款打印机型号 设置的最高速度仅可在理想条件下达到。
         */
        public static String getSPEED(int level) {
            return String.format(SPEED, level);
        }

        public static String getUNDERLINE() {
            return UNDERLINE;
        }

        public static String getSETBOLD(int value) {
            return String.format(SETBOLD, value);
        }

        /**
         * TEXT 命令用于在标签上添加文本。这项命令及其各衍生命令可以控制使用的具体字体号 和大小、标签上文本的位置以及文本的方向。标准常驻字体能够以 90 度的增量旋转，如下例 所示。
         * {font}：字体名称/编号。 - 1 ==> 8×12（英文字体） - 55 ==> 16×16（GB18030） - 4 ==> 32×32（GB18030） - 其他默认 ==> 24×24（GB2312）
         * {size}：字体的大小标识。 - 有效字体大小范围是 0 至 7。
         * {x}：横向起始位置。
         * {y}：纵向起始位置。
         * {data}：要打印的文本。
         */
        public static String getTEXT(TextEnum textEnum,  int font, int size,  int x, int y, String data) {
            return String.format(TEXT, textEnum.name(), font, size, x, y, data);
        }

        /**
         * 使用 LINE 命令可以绘制任何长度、宽度和角度方向的线条。
         * {command}： 从下面选择一项： LINE（或 L）：打印线条。
         * {x1}：左上角的 X 坐标。
         * {y1}：左上角的 Y 坐标。
         * {x2}：以下项的 X 坐标： - 水平轴的右上角。 - 垂直轴的左下角。
         * {y2}：以下项的 Y 坐标： - 水平轴的右上角。 - 垂直轴的左下角。
         * {width}：线条的单位宽度
         */
        public static String getLINE(Integer x1, Integer y1, Integer x2, Integer y2, Integer width) {
            Objects.requireNonNull(x1);
            Objects.requireNonNull(y1);
            Objects.requireNonNull(x2);
            Objects.requireNonNull(y2);
            return String.format(LINE, x1, y1, x2, y2, width);
        }

        public static String getInverseLine() {
            return INVERSE_LINE;
        }

        public static String getBOX() {
            return BOX;
        }

        public static String getTONE() {
            return TONE;
        }

        /**
         * 打印机假定页面宽度为打印机的完整宽度。打印会话的最大高度由页面宽度和可用打印内 存决定。
         * 如果页面宽度小于打印机的完整宽度，则用户可以通过指定页面宽度来增加最大页面 高度
         * {width}：页面的单位宽度。
         */
        public static String getPageWidth(Integer width) {
            Objects.requireNonNull(width, "start qty can not null");
            return String.format(PAGE_WIDTH, width);
        }

        /**
         * 条码开始的必要
         *{font number}：注释条码时要使用的字体号。
         * {font size}：注释条码时要使用的字体大小。
         * {offset}：文本距离条码的单位偏移量。
         */
        public static String getBarcodeTextStart(int fontNumber, int fontSize, int offset) {
            return String.format(BARCODE_TEXT_START, fontNumber, fontSize, offset);
        }

        public static String getBarcodeTextEnd() {
            return BARCODE_TEXT_END;
        }

        /**
         * 命令能够以指定的宽度和高度纵向和横向打印条码。
         * {command}：从下面选择一项： BARCODE（或 B）：横向打印条码。 VBARCODE（或 VB） 纵向打印条码。
         * {type}：从下表中选择：
         * UPCA、UPCA2、UPCA5
         * UPCE、UPCE2、UPCE5
         * EAN13、EAN132、EAN135
         * EAN8、EAN82、EAN 85
         * 39、39C、F39、F39C
         * 93
         * I2OF5
         * I2OF5C
         * I2OF5G
         * 128
         * UCCEAN128
         * CODABAR、CODABAR16
         * MSI、MSI10、MSI1010、MSI1110
         * POSTNET
         * FIM
         */
        public static String getBARCODE(int type, int width, int ratio, int height, int x, int y, String data) {
            return String.format(BARCODE, type, width, ratio, height, x, y, data);
        }

        /**
         * 二维码命令行
         * barcodeEnum：从下面选择一项： BARCODE（或 B）：横向打印条码。 VBARCODE（或 VB）：纵向打印条码。
         * {type}：QR
         * {x}：横向起始位置。
         * {y}：纵向起始位置。
         * [M n]：QR Code 规范编号。选项是 1 或 2。QR Code Model 1 是原始规范，而 QR Code Model 2 则是该符号的经过增强后的形式。Model 2 提供了附加功能，而 且可以自动与 Model 1 进行区分。Model 2 为推荐规范，是默认值。
         * [U n]：模块的单位宽度/单位高度。 范围是 1 至 32。默认值为 6。
         * {data}：提供生成 QR Code 所需的信息。请参见下面的示例。
         * {data} 除了包含实际的输入数据字符串外，还包含一些模式选择符号。输入数据类型可以 由打印机软件自动识别，也可以通过手动方式设置。模式选择符号和实际数据之间 有一个分隔符（逗号）。
         */
        public static String getQRCODE(BarcodeEnum barcodeEnum, int x, int y, int size, String data) {
            return String.format(QRCODE, barcodeEnum.name(), "QR", x, y, 2, size, data);
        }

        public static String getBEEP() {
            return BEEP;
        }

        public static String getJOURNAL() {
            return JOURNAL;
        }

        public static String getSENSE() {
            return SENSE;
        }

        public static String getGRAPHICS() {
            return GRAPHICS;
        }
    }

    public enum TextEnum {

        /**
         * 横向打印文本。
         */
        TEXT,

        /**
         * 逆时针旋转 90 度，纵向打印文本。
         */
        VTEXT,

        /**
         * 逆时针旋转 90 度，纵向打印文本。
         */
        TEXT90,

        /**
         * 逆时针旋转 80 度，反转打印文本。
         */
        TEXT180,

        /**
         * 逆时针旋转 270 度，纵向打印文本。
         */
        TEXT270,
        ;
    }

    public enum BarcodeEnum {

        /**
         * 横向
         */
        BARCODE,

        /**
         * 纵向
         */
        VBARCODE,
    }

    public enum GraphicsEnum {

        /**
         * 横向打印扩展图形
         */
        EXPANDED_GRAPHICS,

        /**
         * 纵向打印扩展图形。
         */
        VEXPANDED_GRAPHICS,

        /**
         * 横向打印压缩图形。
         */
        COMPRESSED_GRAPHICS,

        /**
         * 纵向打印压缩图形。
         */
        VCOMPRESSED_GRAPHICS
    }
}
