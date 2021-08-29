package com.xsg.common.printer.action;

import com.xsg.common.printer.model.XsgFont;

import java.util.Objects;

/**
 * @author 高总辉
 * @desc 画笔
 * @date 2020/11/26 3:55 下午
 */
public abstract class BoardPaint<T> {

    /**
     * 画板
     */
    protected final T board;

    protected BoardPaint(T board) {
        Objects.requireNonNull(board, "board can not null");
        this.board = board;
    }

    /**
     * 画线
     * @param
     */
    public abstract void drawLine(int x1, int y1, int x2, int y2, int borderType, int color);

    /**
     * 画二维码
     */
    public abstract void drawQrCode (String value, int x, int y, int width, int height, boolean isCompress);

    /**
     * 画条形码
     */
    public abstract void drawBarCode (String value, int x, int y, int width, int height, boolean isCompress);

    /**
     * 画图片
     * @param
     */
    public abstract void drawImage (String path, int x, int y, int width, int height, boolean isCompress);

    /**
     * 画文字
     * @param
     */
    public abstract int drawString (String str, int x, int y, int x0, int y0, XsgFont font);
}
