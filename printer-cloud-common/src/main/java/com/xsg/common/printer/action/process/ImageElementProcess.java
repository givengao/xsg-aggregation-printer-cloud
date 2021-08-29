package com.xsg.common.printer.action.process;

import com.xsg.common.printer.action.BoardPaint;
import com.xsg.common.printer.model.XsgImageElement;
import com.xsg.common.printer.model.XsgMargin;

/**
 * @author 高总辉
 * @desc
 * @date 2020/11/24 11:13 上午
 */
public class ImageElementProcess extends ElementProcess<XsgImageElement> {

    @Override
    int execute(BoardPaint paint, XsgImageElement element) {
        if (element == null) {
            return 0;
        }
        String path = element.getFiled().getValue();
        if (path == null) {
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
        try {
            paint.drawImage(path, startX, startY, drawWidth, drawHeight, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
