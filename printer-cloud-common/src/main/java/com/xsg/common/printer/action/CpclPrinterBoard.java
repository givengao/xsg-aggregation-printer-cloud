package com.xsg.common.printer.action;

/**
 * @author 高总辉
 * @desc GPrinter
 * @date 2020/11/27 1:48 下午
 */
public class CpclPrinterBoard extends VoucherBoard<CpclPrinterGraphics>{

    CpclPrinterGraphics cpclPrinterGraphics;

    public CpclPrinterBoard(int height, int width, int bgColor) {
        super(height, width, bgColor);
    }

    @Override
    public BoardPaint<CpclPrinterGraphics> createBoard() {
        cpclPrinterGraphics = new CpclPrinterGraphics(getWidth(), getHeight());
        return new CpclPrinterBoardPaint(cpclPrinterGraphics);
    }

    @Override
    public String getResult() {
        cpclPrinterGraphics.end();
        return cpclPrinterGraphics.getCommand();
    }
}
