package com.xsg.common.printer.model;

//import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.function.Function;

/**
 * @author 高总辉
 * @desc 字段
 * @date 2020/11/23 11:19 上午
 */
@Data
@Accessors(chain = true)
public class XsgFiled<T> {

    /**
     * 标题
     */
    private String title;

    /**
     * 字段属性名
     */
    private String name;

    /**
     * 属性值
     */
    private String value;

    /**
     * 是否自动换行
     */
    private boolean needFeedLine = true;

    /**
     * 是否自适应宽度
     */
    private boolean fit = false;

    /**
     * 属性取值函数
     */
//    @JsonIgnore
    private Function<T, String> valueFunction;
}
