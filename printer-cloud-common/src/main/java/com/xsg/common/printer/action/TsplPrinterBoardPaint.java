package com.xsg.common.printer.action;

import com.xsg.common.printer.model.XsgFont;

/**
 * @author 高总辉
 * @desc
 * @date 2020/12/2 4:55 下午
 */
public class TsplPrinterBoardPaint extends BoardPaint<TsplPrinterGraphics> {

    protected TsplPrinterBoardPaint(TsplPrinterGraphics board) {
        super(board);
    }

    @Override
    public void drawLine(int x1, int y1, int x2, int y2, int borderType, int color) {
        if (borderType == 0) {
            return;
        }
        board.drawLine(x1, y1, x2, y2, borderType, color);
    }

    @Override
    public void drawQrCode(String value, int x, int y, int width, int height, boolean isCompress) {
        board.drawQrCode(value, x, y, width, height, isCompress);
    }

    @Override
    public void drawBarCode(String value, int x, int y, int width, int height, boolean isCompress) {
        board.drawBarCode(value, x, y, width, height, isCompress);
    }

    @Override
    public void drawImage(String path, int x, int y, int width, int height, boolean isCompress) {
        board.drawImage(path, x, y, width, height, isCompress);
    }

    @Override
    public int drawString(String str, int x, int y, int x0, int y0, XsgFont font) {
        return board.drawString(str, x, y, x0, y0, font);
    }
}
