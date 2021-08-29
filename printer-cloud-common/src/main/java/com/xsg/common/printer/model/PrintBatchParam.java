package com.xsg.common.printer.model;

import lombok.Data;

import java.util.List;

/**
 * @author 高总辉
 * @desc
 * @date 2020/12/17 10:58 上午
 */
@Data
public class PrintBatchParam<T> {
    /**
     * 模版
     */
    private T template;

    /**
     * 打印主数据 注：属性值不支持List,Map,类对象
     */
    private List<Object> params;
}
