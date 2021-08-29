package com.xsg.common.printer.action;

import com.xsg.common.printer.constants.FontSizeEnum;
import com.xsg.common.printer.constants.BaseConstants;
import com.xsg.common.printer.model.FeedLine;
import com.xsg.common.printer.model.FontDrawSize;
import com.xsg.common.printer.model.XsgFont;
import com.xsg.common.printer.utils.DrawUtil;
import lombok.Data;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * @author 高总辉
 * @desc
 * @date 2020/12/2 9:09 下午
 */
@Data
public class TsplPrinterGraphics {

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

    public TsplPrinterGraphics(int width, int height, int gap) {
        this.width = width;
        this.height = height;
        this.gap = gap;
        init(width, height, gap);
    }

    public void init (int width, int height, int gap) {
        addCommand(String.format(TPrinterCommand.SIZE, width/8, height/8));
        addCommand(String.format(TPrinterCommand.GAP, gap, 0));
        addCommand(String.format(TPrinterCommand.CLS));
    }

    public void drawLine(int x1, int y1, int x2, int y2, int borderType, int color) {
        int width = x2 - x1;
        int height = y2 - y1;
        if (width > 0) {
            addCommand(String.format(TPrinterCommand.BAR, x1, y1, width, 1));
        }
        if (height > 0) {
            addCommand(String.format(TPrinterCommand.BAR, x1, y1, 1, height));
        }
    }

    public void drawQrCode(String value, int x, int y, int width, int height, boolean isCompress) {
        addCommand(String.format(TPrinterCommand.QRCODE, x, y, "L", 7, "A", 0,value ));
    }

    public void drawBarCode(String value, int x, int y, int width, int height, boolean isCompress) {
        addCommand(String.format(TPrinterCommand.BARCODE, x, y, "39", height, 1,0,2,4,value));
    }

    public void drawImage(String path, int x, int y, int width, int height, boolean isCompress) {
        //todo ....
        String imageText = DrawUtil.getSpecialStr(path);
        if (imageText != null) {
            //业务特殊处理 todo...需要做改造
            if ("T".equals(imageText)) {
                addCommand(String.format(TPrinterCommand.TEXT, x, y, "5", 0, 2, 1, imageText));
                addCommand(String.format(TPrinterCommand.TEXT, x + 1, y + 1, "5", 0, 2, 1, imageText));
                addCommand(String.format(TPrinterCommand.TEXT, x + 2, y + 2, "5", 0, 2, 1, imageText));
            } else {
                FontDrawSize fontDrawSize = BaseConstants.getTsplFontDrawSize(FontSizeEnum.BIG);
                addCommand(String.format(TPrinterCommand.TEXT, x, y, fontDrawSize.getFontNameOrSize(), 0, 1, 1, imageText));
                addCommand(String.format(TPrinterCommand.TEXT, x + 1, y +1, fontDrawSize.getFontNameOrSize(), 0, 1, 1, imageText));
            }
        }
    }

    public int drawString(String str, int x, int y, int x0, int y0, XsgFont font) {
        int width = x0 - x;
        int height = y0 - y;

        FontDrawSize fontDrawSize = BaseConstants.getTsplFontDrawSize(font.getFontSizeEnum());
        if (font.isNeedFeedLine()) {
            List<FeedLine> feedLines = DrawUtil.feedLine(height, width, str, fontDrawSize.getChineseHeight(), fontDrawSize.getEnglishHeight(), fontDrawSize.getChineseWidth(), fontDrawSize.getEnglishWidth());
            for (FeedLine feedLine : feedLines) {
                addCommand(String.format(TPrinterCommand.TEXT, x, y, fontDrawSize.getFontNameOrSize(), 0, 1, 1, feedLine.getLine()));
                if (font.isBold()) {
                    addCommand(String.format(TPrinterCommand.TEXT, x + 1, y +1, fontDrawSize.getFontNameOrSize(), 0, 1, 1, feedLine.getLine()));
                }
                y = y + feedLine.getHeight();
            }
        } else {
            addCommand(String.format(TPrinterCommand.TEXT, x, y, fontDrawSize.getFontNameOrSize(), 0, 1, 1, str));
            if (font.isBold()) {
                addCommand(String.format(TPrinterCommand.TEXT, x + 1, y +1, fontDrawSize.getFontNameOrSize(), 0, 1, 1, str));
            }
            if (font.isFit()) {
                int strWidth = fontDrawSize.getTotalWidth(str);
//                if (strWidth > width) {
                    return strWidth - width;
//                }
            }
        }
        return 0;
    }

    public void end () {
        addCommand(String.format(TPrinterCommand.PRINT, 1, 1));
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

    public static class TPrinterCommand {

        /**
         * 设定卷标纸的宽度和长度
         * ｍ 标签纸的宽度（不含背纸）
         * n 标签纸的长度（不含背纸）
         */
        public static final String SIZE = "SIZE {%s} mm,{%s} mm";

        /**
         * 该指令用于定义两张卷标纸间的垂直间距距离
         * m 两标签纸中间的垂直距离 0≤m≤1（inch），0≤m≤25.4（mm）
         * n 垂直间距偏移 n≤标签纸纸张长度（inch 或 mm）
         */
        public static final String GAP = "GAP {%s} mm,{%s} mm";

        /**
         * 该指令用于设定黑标高度及定义标签印完后标签额外送出的长度
         * m 黑标高度 0≤m≤1（inch），0≤m≤25.4（mm）
         * n 额外送出纸张长度 n≤标签纸纸张长度（inch 或 mm）
         */
        public static final String BLINE = "BLINE {%s} mm,{%s} mm";

        /**
         * 在打印 下一张时打印机会将原先多推出或少推出的部分以回拉方式补偿回来
         * 该指令仅 适用于剥离模式
         * m 纸张停止的距离（inch 或 mm） 0 ≤ m ≤ 1（inch） 0 ≤ m ≤ 25.4（mm）
         */
        public static final String OFFSET = "OFFSET {%s} mm";

        /**
         * n
         * 1.5 设定打印速度为 1.5＂/sec
         * 2.0 设定打印速度为 2.0＂/sec
         * 3.0 设定打印速度为 3.0＂/sec
         * 4.0 设定打印速度为 4.0＂/sec
         */
        public static final String SPEED = "SPEED {%s}";

        /**
         * n
         * 0～15 0：使用最淡的打印浓度
         * 15：使用最深的打印浓度
         */
        public static final String DENSITY = "DENSITY {%s}";

        /**
         * 出纸方向
         * n
         * 0反向 或 1正向，请参考图示
         */
        public static final String DIRECTION = "DIRECTION {%s}";

        /**
         * 该指令用于定义卷标的参考坐标原点。坐标原点 位置和打印方向有关，
         * x 水平方向的坐标位置,单位 dot
         * y 垂直方向的坐标位置,单位 dot
         */
        public static final String REFERENCE = "REFERENCE {%s},{%s}";

        /**
         * 该指令用于选择对应的国际字符集
         * n
         * 001:USA
         * 002:French
         * 003:Latin America
         * 034:Spanish
         * 039:Italian
         * 044:United Kingdom
         * 046:Swedish
         * 047:Norwegian
         * 049:German
         */
        public static final String COUNTRY = "COUNTRY {%s}";

        /**
         * 该指令用于选择对应的国际代码页
         * 437:United States
         * .
         * .
         * .
         */
        public static final String CODEPAGE = "CODEPAGE {%s}";

        /**
         * 该指令用于清除图像缓冲区（image buffer)的数据
         */
        public static final String CLS = "CLS";

        /**
         * n
         * 该指令用于将标签纸向前推送指定的长度
         * 1≤n≤9999，单位 dot
         */
        public static final String FEED = "FEED {%s}";

        /**
         * n
         * 该指令用于将标签纸向后回拉指定的长度
         * 1≤n≤9999，单位 dot
         */
        public static final String BACKFEED = "BACKFEED {%s}";

        /**
         * n
         * 该指令用于将标签纸向后回拉指定的长度
         * 1≤n≤9999，单位 dot
         */
        public static final String BACKUP = "BACKUP {%s}";

        /**
         * 该指令用于控制打印机进一张标签纸
         */
        public static final String FORMFEED = "FORMFEED";

        /**
         * 在使用含有间隙或黑标的标签纸时，若不能确定第一张标签纸是否在正确打印位 置时，此指令可将标签纸向前推送至下一张标签纸的起点开始打印。标签尺寸和 间隙需要在本条指令前设置。
         * 注：使用该指令时，纸张高度大于或等于 30 mm
         */
        public static final String HOME = "HOME";

        /**
         * 该指令用于打印出存储于影像缓冲区内的数据
         * m 指定打印的份数（set） 1≤m≤65535
         * n 每张标签需重复打印的张数 1≤n≤65535
         */
        public static final String PRINT = "PRINT {%s},{%s}";

        /**
         * 该指令用于控制蜂鸣器的频率，可设定 10 阶的声音，每阶声音的长短由第二个 参数控制
         * level 音阶:0-9 interval
         * 间隔时间:1-4095
         */
        public static final String SOUND = "SOUND {%s},{%s}";

        /**
         * 该指令用于设定打印机进纸时，若经过所设定的长度仍无法侦测到垂直间距，则 打印机发生错误，停止进纸。
         * n
         * 可使用 inch 或 mm
         */
        public static final String LIMITFEED = "LIMITFEED {%s} mm";

        /**
         * x 线条左上角 X 坐标，单位 dot
         * y 线条左上角 Y 坐标，单位 dot
         * width 线宽，单位 dot
         * height 线高，单位 dot
         */
        public static final String BAR = "BAR {%s},{%s},{%s},{%s}";

        /**
         * x 左上角水平坐标起点，以点（dot）表示
         * y 左上角垂直坐标起点，以点（dot）表示
         * height 条形码高度，以点（dot）表示
         * human readable 0 表示人眼不可识，1 表示人眼可识
         * rotation 条形码旋转角度，顺时针方向
         *      0，不旋转
         *      90，顺时针方向旋转
         *      90 度 180，顺时针方向旋转
         *      180 度 270，顺时针方向旋转
         *      270 度
         * narrow 窄 bar 宽度，以点（dot）表示
         * wide 宽 bar 宽度，以点（dot）表示
         * code type 详见表 1
         * content 正文
         * 语句：BARCODE x,y,"code type",height,human readable,rotation,narrow,wide,"content"
         * 例子：BARCODE 100,100,"39",96,1,0,2,4,"1000"
         */
        public static final String BARCODE = "BARCODE {%s},{%s},\"{%s}\",{%s},{%s},{%s},{%s},{%s},\"{%s}\"";

        /**
         * 该指令用于在卷标上绘制矩形方框
         * x_start 方框左上角 X 坐标，单位 dot
         * y_start 方框左上角 Y 坐标，单位 dot
         * x_end 方框右下角 X 坐标，单位 dot
         * y_end 方框右下角 Y 坐标，单位 dot
         * line thickness 方框线宽，单位 dot
         * 语句：BOX x_start,y_start,x_end,y_end,line thickness
         * 例子：BOX 100,100,200,200,5
         */
        public static final String BOX = "BAR {%s},{%s},{%s},{%s},{%s}";

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
        public static final String BITMAP = "BITMAP {%s},{%s},{%s},{%s},{%s},{%s}";

        /**
         * 该指令用来打印单色 BMP 图片文件
         * x 水平方向起始点坐标，以点（dot）表示
         * y 垂直方向起始点坐标，以点（dot）表示
         * filename 欲打印的文件名称（需已下载于打印机缓存)
         * 语句：PUTBMP x,y,"filename"
         * 例子：PUTBMP 100,100,"LOGO.BMP"
         */
        public static final String PUTBMP = "PUTBMP {%s},{%s},\"{%s}\"";

        /**
         * 该指令用于打印单色 PCX 格式图片文件
         * x 水平方向起始点坐标，以点（dot）表示
         * y 垂直方向起始点坐标，以点（dot）表示
         * filename 欲打印的文件名称（需已下载于打印机缓存)
         * 语句：PUTPCX x,y,"filename"
         * 例子：PUTPCX 10,10,"TSC.PCX"
         */
        public static final String PUTPCX = "PUTPCX {%s},{%s},\"{%s}\"";

        /**
         * 该指令用于清除影像缓冲区部分区域的数据
         * x_start 清除区域的左上角 X 座标，单位 dot
         * y_start 清除区域的左上角 Y 座标，单位 dot
         * x_width 清除区域宽度，单位 dot
         * y_height 清除区域宽度，单位 dot
         * 语句：ERASE x_start,y_start,x_width,y_height
         * 例子：BAR 100,100,300,300
         *      ERASE 150,150,200,200
         */
        public static final String ERASE = "ERASE {%s},{%s},{%s},{%s}";

        /**
         * 将指定的区域反相打印
         * x_start 反相区域左上角 X 坐标，单位 dot
         * y_start 反相区域左上角 Y 坐标，单位 dot
         * x_width 反相区域宽度，单位 dot
         * y_height 反相区域高度，单位 dot
         * 语句：REVERSE x_start,y_start,x_width,y_height
         * 例子：REVERSE 100,100,200,200
         */
        public static final String REVERSE = "REVERSE {%s},{%s},{%s},{%s}";

        /**
         * 该指令用于打印字符串
         * x 文字 X 方向起始点坐标
         * y 文字 Y 方向起始点坐标
         * font 字体名称
         *      1 8×12 dot 英数字体
         *      2 12×20 dot 英数字体
         *      3 16×24 dot 英数字体
         *      4 24×32 dot 英数字体
         *      5 32×48 dot 英数字体
         *      6 14×19 dot 英数字体 OCR-B
         *      7 21×27 dot 英数字体 OCR-B
         *      8 14×25 dot 英数字体 OCR-A
         *      9 9×17 dot 英数字体
         *      10 12×24 dot 英数字体
         *      TST24.BF2 繁体中文 24×24Font(大五码）
         *      TSS24.BF2 简体中文 24×24Font（GB 码）
         *      K 韩文 24×24Font（KS 码）
         * rotation 文字旋转角度（顺时针方向）
         *      0   0   度
         *      90  90  度
         *      180 180 度
         *      270 270 度
         * x-multiplication X 方向放大倍率 1-10
         * y-multiplication Y 方向放大倍率 1-10
         * content 正文
         * 语句：TEXT x,y,"font",rotation,x-multiplication,y-multiplication,"content"
         * 例子：TEXT 100,100,"4",0,1,1,"DEMO FOR TEXT"
         */
        public static final String TEXT = "TEXT {%s},{%s},\"{%s}\",{%s},{%s},{%s},\"{%s}\"";

        /**
         * 该指令用来打印二维码
         * ｘ 二维码水平方向起始点坐标
         * ｙ 二维码垂直方向起始点坐标
         * ECC level->选择QRCODE纠错等级
         *      L 7%
         *      M 15%
         *      Q 25%
         *      H 30%
         * cell width 二维码宽度 1-10
         * mode 手动/自动编码
         *      A Auto
         *      M Manual
         * rotation 旋转角度（顺时针方向）
         *      0   0   度
         *      90  90  度
         *      180 180 度
         *      270 270 度
         * data string 编码的字符串
         * 语句：QRCODE x,y,ECC level,cell width,mode,rotation,"data string"
         * 例子：
         * SIZE 60 mm,30 mm
         * GAP 2 mm
         * CLS
         * QRCODE 20,20,L,4,A,0,"www.gainscha.com"
         * PRINT 1,1
         */
        public static final String QRCODE = "QRCODE {%s},{%s},{%s},{%s},{%s},{%s},\"{%s}\"";
    }
}
