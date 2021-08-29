package com.xsg.common.printer.constants;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author 高总辉
 * @desc 打印机设备类型
 * @date 2020/12/22 4:09 下午
 */
public enum DeviceTypeEnum {

    BLUETOOTH("BLUETOOTH", "蓝牙打印机", 0, Lists.newArrayList(VoucherEnum.INSTRUCT_TSPL, VoucherEnum.INSTRUCT_CPCL, VoucherEnum.PDF)),

    CLOUD("CLOUD", "汉印云", 1, Lists.newArrayList(VoucherEnum.IMAGE)),

    NATIVE_CLOUD("NATIVE_CLOUD", "USB连接云打印机器", 2, Lists.newArrayList(VoucherEnum.INSTRUCT_TSPL, VoucherEnum.INSTRUCT_CPCL, VoucherEnum.PDF)),

    KUAIDI100("KUAIDI100", "快递100", 3, Lists.newArrayList(VoucherEnum.IMAGE)),

    FHD001("FHD001", "打印猿", 4, Lists.newArrayList(VoucherEnum.TEMPLATE)),

    SELF_HELP("SELF_HELP", "自助打印", 5, Lists.newArrayList(VoucherEnum.INSTRUCT_TSPL, VoucherEnum.INSTRUCT_CPCL, VoucherEnum.PDF));

    private String code;
    private String name;
    private Integer type;

    private List<VoucherEnum> supportedInstructions;

    DeviceTypeEnum(String code, String name, Integer type, List<VoucherEnum> supportedInstructions) {
        this.code = code;
        this.name = name;
        this.type = type;
        this.supportedInstructions = supportedInstructions;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public List<VoucherEnum> getSupportedInstructions() {
        return supportedInstructions;
    }

    public void setSupportedInstructions(List<VoucherEnum> supportedInstructions) {
        this.setSupportedInstructions(supportedInstructions);
    }

    /**
     * 根据code查询name
     *
     * @param type
     * @return
     */
    public static String getNameByType(Integer type) {
        for (DeviceTypeEnum value : DeviceTypeEnum.values()) {
            if (value.getType().equals(type)) {
                return value.getName();
            }
        }
        return null;
    }

    /**
     * 根据type查询code
     *
     * @param type
     * @return
     */
    public static String getCodeByType(Integer type) {
        for (DeviceTypeEnum value : DeviceTypeEnum.values()) {
            if (value.getType().equals(type)) {
                return value.getCode();
            }
        }
        return null;
    }
}
