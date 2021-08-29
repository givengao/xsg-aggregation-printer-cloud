package com.xsg.common.printer.action;

/**
 * @author 高总辉
 * @desc
 * @date 2020/12/3 2:32 下午
 */
public class TsplPrinterBoard extends VoucherBoard<TsplPrinterGraphics> {

    private TsplPrinterGraphics tsplPrinterGraphics;

    public TsplPrinterBoard(int height, int width, int bgColor) {
        super(height, width, bgColor);
    }

    @Override
    public BoardPaint<TsplPrinterGraphics> createBoard() {
        tsplPrinterGraphics = new TsplPrinterGraphics(getWidth(), getHeight(), 2);
        return new TsplPrinterBoardPaint(tsplPrinterGraphics);
    }

    @Override
    public String getResult() {
        tsplPrinterGraphics.end();
        return tsplPrinterGraphics.getCommand();
    }
}
