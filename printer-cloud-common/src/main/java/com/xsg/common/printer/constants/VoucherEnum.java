package com.xsg.common.printer.constants;

/**
 * @author 高总辉
 * @desc 面单类型
 * @date 2020/11/26 3:14 下午
 */
public enum  VoucherEnum {

    PDF("PDF", "pdf文件"),

    IMAGE("IMAGE", "图片"),

    INSTRUCT_CPCL("INSTRUCT_CPCL", "CPCL指令集"),

    INSTRUCT_TSPL("INSTRUCT_TSPL", "TSPL指令集"),

    INSTRUCT_TSPL_HPRT("INSTRUCT_TSPL_HPRT", "汉印云TSPL指令集"),

    TEMPLATE("TEMPLATE", "云平台提供模板，无需指令");

    private String code;
    private String name;

    public String getCode(){
        return this.code;
    }

    public String getName(){
        return this.name;
    }

    VoucherEnum (String code, String name) {
        this.code = code;
        this.name = name;
    }
}
