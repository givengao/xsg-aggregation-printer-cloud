package com.xsg.common.printer.action;

import lombok.Data;

/**
 * @author 高总辉
 * @desc 面单画版
 * @date 2020/11/26 4:41 下午
 */
@Data
public abstract class VoucherBoard<T> {

    /**
     * 高度
     */
    private final int height;

    /**
     * 宽度
     */
    private final int width;

    /**
     * 背景颜色
     */
    private final int bgColor;

    /**
     * 画板内容
     */
    private T board;


    public VoucherBoard(int height, int width, int bgColor) {
        this.height = height;
        this.width = width;
        this.bgColor = bgColor;
    }

    /**
     * 创建画笔
     * @return
     */
    public abstract BoardPaint<T> createBoard ();

    public abstract String getResult ();
}
