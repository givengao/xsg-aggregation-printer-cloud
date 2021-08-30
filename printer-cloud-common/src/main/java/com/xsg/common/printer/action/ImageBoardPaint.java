package com.xsg.common.printer.action;

import com.xsg.common.printer.constants.FontSizeEnum;
import com.xsg.common.printer.constants.BaseConstants;
import com.xsg.common.printer.model.XsgFont;
import com.xsg.common.printer.utils.*;

import java.awt.*;
import java.util.List;

/**
 * @author 高总辉
 * @desc
 * @date 2020/11/26 5:01 下午
 */
public class ImageBoardPaint extends BoardPaint<Graphics2D> {

    private static final int DefaultFontSize = 20;

    public ImageBoardPaint(Graphics2D board) {
        super(board);
    }

    @Override
    public void drawLine(int x1, int y1, int x2, int y2, int borderType, int color) {
        //不画线
        if (borderType == 0) {
            return;
        }
        board.setColor(BaseConstants.getColor(color));
        //画线样式
        switch (borderType) {
            case 2:
                //画虚线
                BasicStroke stroke = new BasicStroke(2.5f, BasicStroke.CAP_BUTT,
                        BasicStroke.JOIN_ROUND, 3.5f, new float[] { 10, 5 }, 0f);
                board.setStroke(stroke);
                board.drawLine(x1 , y1 , x2 , y2 );
                break;
            default:
                //画实线
                //边框加粗
                board.setStroke(new BasicStroke(2.0f));
                board.drawLine(x1 , y1 , x2 , y2 );
        }
    }

    @Override
    public void drawQrCode(String value, int x, int y, int width, int height, boolean isCompress) {
        try {
            String path = QRCodeUtil.drawQRCode(value, width , height);
            if (path != null) {
                Image image = ImageUtil.readImage(path, width, height, isCompress);
                board.drawImage(image, x , y , null);
                FileWriterUtils.clearFile(path);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void drawBarCode(String value, int x, int y, int width, int height, boolean isCompress) {
        Image image = null;
        String path = null;
        try {
            path = BarcodeUtil.drawBarcode(value);
            image = ImageUtil.readImage(path, width , height , isCompress);
        } catch (Exception e) {
            e.printStackTrace();
        }
        board.drawImage(image, x, y, null);
        if (path != null) {
            FileWriterUtils.clearFile(path);
        }
    }

    @Override
    public void drawImage(String path, int x, int y, int width, int height, boolean isCompress) {
        Image image = null;
        try {
            image = ImageUtil.readImage(path, width , height , true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        board.drawImage(image, x , y , null);
    }

    @Override
    public int drawString(String str, int x, int y, int x0, int y0, XsgFont xsgFont) {
        if (str == null || str.length() <= 0) {
            return 0;
        }
        int width = x0 - x;
        int height = y0 - y;
        board.setColor(Color.BLACK);
        board.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        //消除文本出现锯齿现象
        String fontName = BaseConstants.getFontName(xsgFont.getFontNameType());
        fontName = fontName == null ? "黑体" : fontName;
        int fontSize = getFontSize(xsgFont.getFontSizeEnum());
        Font font = new Font(fontName, xsgFont.isBold() ? Font.BOLD : Font.PLAIN, fontSize);
        board.setFont(font);
        FontMetrics fontMetrics = board.getFontMetrics();
        int strWidth = fontMetrics.charsWidth(str.toCharArray(), 0, str.length());
        //文字绘制高度
        int ascent = fontMetrics.getAscent();
        if (xsgFont.isNeedFeedLine()) {
            List<String> feedLines = DrawUtil.feedImageLine(height, width, str, fontMetrics);
            y = y + ascent;
            for (int i = 0; i < feedLines.size(); i++) {
                String feedLine = feedLines.get(i);
                if (i == 0) {
                    board.drawString(feedLine, x , y);
                } else {
                    board.drawString(feedLine, x , y + 5);
                }
                y = y + ascent;
            }
        } else {
            y = y + ascent;
            board.drawString(str, x , y);
            if (xsgFont.isFit()) {
                return strWidth - width;
            }
        }
        return 0;
    }

    /**
     * 获取字体大小
     * @param fontSizeEnum
     * @return
     */
    private int getFontSize (FontSizeEnum fontSizeEnum) {
        if (fontSizeEnum == null) {
            return DefaultFontSize;
        }
        switch (fontSizeEnum) {
            case BIG:
                return DefaultFontSize * 2 - 5;
            case MIDDLE:
                return DefaultFontSize + 5;
            case SMALL:
                return DefaultFontSize + 2;
            default:
                return DefaultFontSize;
        }
    }
}
