package com.xsg.common.printer.vo;

import lombok.Data;

/**
 * @author 高总辉
 * @desc
 * @date 2020/12/1 11:14 上午
 */
@Data
public class TemplateVo {

    /**
     * 公司名称
     */
    private String cname;

    /**
     * 街道地址
     */
    private String streetName;

    /**
     * 客服电话
     */
    private String tel;

    /**
     * 寄件方名称
     */
    private String senderName;

    /**
     * 寄件联系人
     */
    private String senderContactName;

    /**
     * 寄件联系人手机
     */
    private String senderContactPhone;

    /**
     * 寄件省名称
     */
    private String senderProvinceName;

    /**
     * 寄件市名称
     */
    private String senderCityName;

    /**
     * 寄件区县名称
     */
    private String senderCountyName;

    /**
     * 寄件街道名称
     */
    private String senderStreetName;

    /**
     * 寄件详细地址
     */
    private String senderDetailAddress;

    /**
     * 寄件全地址
     */
    private String senderFullAddress;

    /**
     * 收件方名称
     */
    private String addresseeName;

    /**
     * 收件联系人
     */
    private String addresseeContactName;

    /**
     * 收件联系手机
     */
    private String addresseeContactPhone;

    /**
     * 收件省名称
     */
    private String addresseeProvinceName;

    /**
     * 收件市名称
     */
    private String addresseeCityName;

    /**
     * 收件区县名称
     */
    private String addresseeCountyName;

    /**
     * 收件街道名称
     */
    private String addresseeStreetName;

    /**
     * 收件人详细地址
     */
    private String addresseeDetailAddress;

    /**
     * 收件人全地址
     */
    private String addresseeFullAddress;

    /**
     * 订单号码
     */
    private String orderNo;

    /**
     * 数量
     */
    private String qty;

    /**
     * 备注
     */
    private String remark;
}
