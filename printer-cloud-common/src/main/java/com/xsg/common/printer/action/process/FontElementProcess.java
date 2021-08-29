package com.xsg.common.printer.action.process;

import com.xsg.common.printer.action.BoardPaint;
import com.xsg.common.printer.model.XsgFont;
import com.xsg.common.printer.model.XsgFontElement;
import com.xsg.common.printer.model.XsgMargin;

/**
 * @author 高总辉
 * @desc 画文字元素
 * @date 2020/11/23 7:13 下午
 */
public class FontElementProcess extends ElementProcess<XsgFontElement> {

    @Override
    int execute(BoardPaint paint, XsgFontElement element) {
        if (element == null) {
            return 0;
        }
        int startX = element.getStartCoordinate().getX();
        int startY = element.getStartCoordinate().getY();
        int endX = element.getEndCoordinate().getX();
        int endY = element.getEndCoordinate().getY();
        XsgMargin margin = element.getMargin();
        if (margin != null) {
            startX = startX + margin.getLeft();
            startY = startY + margin.getTop();
            endX = endX - margin.getRight();
            endY = endY - margin.getBottom();
        }
//        String show = null;
//        if (element.getFiled().getValue() != null && !element.getFiled().getValue().isEmpty()) {
//            show = new StringBuilder()
//                    .append(element.getFiled().getTitle() == null ? "" : element.getFiled().getTitle())
//                    .append(element.getFiled().getValue()).toString();
//        }
        String show = new StringBuilder()
                .append(element.getFiled().getTitle() == null ? "" : element.getFiled().getTitle())
                .append(element.getFiled().getValue()).toString();
        XsgFont xsgFont = new XsgFont();
        xsgFont.setFontSizeEnum(element.getFontSizeEnum());
        xsgFont.setFontSize(element.getFontSize());
        xsgFont.setFontStyle(element.getFontStyle());
        xsgFont.setFontNameType(element.getFontNameType());
        xsgFont.setBold(element.isBold());
        xsgFont.setNeedFeedLine(element.getFiled().isNeedFeedLine());
        xsgFont.setFit(element.getFiled().isFit());
        int fitWidth = paint.drawString(show, startX, startY, endX, endY, xsgFont);
        return fitWidth;
    }
}
