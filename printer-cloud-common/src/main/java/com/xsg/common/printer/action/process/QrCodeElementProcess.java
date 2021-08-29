package com.xsg.common.printer.action.process;

import com.xsg.common.printer.action.BoardPaint;
import com.xsg.common.printer.model.XsgMargin;
import com.xsg.common.printer.model.XsgQrCodeElement;

/**
 * @author 高总辉
 * @desc
 * @date 2020/11/24 2:26 下午
 */
public class QrCodeElementProcess extends ElementProcess<XsgQrCodeElement> {

    @Override
    int execute(BoardPaint paint, XsgQrCodeElement element) {
        String value = element.getFiled().getValue();
        if (value == null) {
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
        int mostHeight = endY - startY;
        int mostWidth = endX - startX;
        int drawHeight = element.getHeight();
        int drawWidth = element.getWidth();
        if (mostHeight < element.getHeight()) {
            drawHeight = mostHeight;
        }
        if (mostWidth < element.getWidth()) {
            drawWidth = mostWidth;
        }
        paint.drawQrCode(value, startX, startY, drawWidth, drawHeight, true);
        return 0;
    }
}
