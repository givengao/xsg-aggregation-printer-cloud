package com.xsg.common.printer.action;

/**
 * @author 高总辉
 * @desc
 * @date 2020/12/21 4:15 下午
 */
public class TsplHprtPrintBoard extends VoucherBoard<TsplHprtPrintGraphics> {

    private TsplHprtPrintGraphics tsplHprtPrintGraphics;

    public TsplHprtPrintBoard(int height, int width, int bgColor) {
        super(height, width, bgColor);
    }

    @Override
    public BoardPaint<TsplHprtPrintGraphics> createBoard() {
        tsplHprtPrintGraphics = new TsplHprtPrintGraphics(getWidth(), getHeight(), 2);
        return new TsplHprtPrintBoardPaint(tsplHprtPrintGraphics);
    }

    @Override
    public String getResult() {
        tsplHprtPrintGraphics.end();
        return tsplHprtPrintGraphics.getCommand();
    }
}
