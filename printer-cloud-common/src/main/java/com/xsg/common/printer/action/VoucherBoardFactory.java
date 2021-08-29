package com.xsg.common.printer.action;

import com.xsg.common.printer.constants.VoucherEnum;

/**
 * @author 高总辉
 * @desc
 * @date 2020/11/26 6:09 下午
 */
public class VoucherBoardFactory {

    /**
     * 创建面板
     * @param width
     * @param height
     * @param voucherEnum
     * @return
     */
    public static VoucherBoard createBoard (int height, int width, int bgColor, VoucherEnum voucherEnum) {
        if (voucherEnum == null) {
            return null;
        }
        switch (voucherEnum) {
            case IMAGE:
                return new ImageBoard(height, width, bgColor);
            case INSTRUCT_CPCL:
                return new CpclPrinterBoard(height, width, bgColor);
            case INSTRUCT_TSPL:
                return new TsplPrinterBoard(height, width, bgColor);
            case PDF:
                return new PdfBoard(height, width, bgColor);
            case INSTRUCT_TSPL_HPRT:
                return new TsplHprtPrintBoard(height, width, bgColor);
        }
        return null;
    }
}
