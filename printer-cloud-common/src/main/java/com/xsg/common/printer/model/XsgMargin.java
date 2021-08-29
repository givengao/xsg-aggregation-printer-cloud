package com.xsg.common.printer.model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author 高总辉
 * @desc 间距
 * @date 2020/11/25 1:53 下午
 */
@Data
@Accessors(chain = true)
public class XsgMargin {

    /**
     * 上间距
     */
    private int top;

    /**
     * 左间距
     */
    private int left;

    /**
     * 右间距
     */
    private int right;

    /**
     * 下间距
     */
    private int bottom;
}
