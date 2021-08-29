//package com.xsg.common.printer.utils;
//
//import cn.hutool.core.util.StrUtil;
//import lombok.extern.log4j.Log4j2;
//
//import javax.crypto.Mac;
//import javax.crypto.SecretKey;
//import javax.crypto.spec.SecretKeySpec;
//import java.util.Arrays;
//import java.util.Map;
//
//@Log4j2
//public class FHD100SignUtil {
//    private static final String ENCODE = "UTF-8";
//
//    public FHD100SignUtil() {
//    }
//
//    public static String sign(Map<String, String> params, String secret) {
//        String[] keys = (String[])params.keySet().toArray(new String[0]);
//        Arrays.sort(keys);
//        StringBuilder query = new StringBuilder();
//        query.append(secret);
//        String[] var7 = keys;
//        int var6 = keys.length;
//
//        for(int var5 = 0; var5 < var6; ++var5) {
//            String key = var7[var5];
//            String value = (String)params.get(key);
//            if (StrUtil.isNotBlank(key) && StrUtil.isNotBlank(value)) {
//                query.append(key).append(value);
//            }
//        }
//
//        query.append(secret);
//        byte[] bytes = encryptHMAC(query.toString(), secret);
//        return byte2hex(bytes);
//    }
//
//    private static byte[] encryptHMAC(String data, String secret) {
//        byte[] bytes = null;
//
//        try {
//            SecretKey secretKey = new SecretKeySpec(secret.getBytes(ENCODE), "HmacMD5");
//            Mac mac = Mac.getInstance(secretKey.getAlgorithm());
//            mac.init(secretKey);
//            bytes = mac.doFinal(data.getBytes(ENCODE));
//            return bytes;
//        } catch (Exception var5) {
//            log.error("风火递签名失败");
//            throw new RuntimeException(var5.toString());
//        }
//    }
//
//    private static String byte2hex(byte[] bytes) {
//        StringBuilder sign = new StringBuilder();
//
//        for(int i = 0; i < bytes.length; ++i) {
//            String hex = Integer.toHexString(bytes[i] & 255);
//            if (hex.length() == 1) {
//                sign.append("0");
//            }
//
//            sign.append(hex.toUpperCase());
//        }
//
//        return sign.toString();
//    }
//}
