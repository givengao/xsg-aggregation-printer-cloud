package com.xsg.common.printer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FHD001PrintTemplate {
    private String userId;

    private DeviceInfo deviceInfo;

    private TemplateInfo templateInfo;

    private Map<String, String> templateData;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DeviceInfo {
        private String devicePid;

        private String deviceId;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class TemplateInfo {
        private String templateId;

         private String templateUrl;
    }
}
