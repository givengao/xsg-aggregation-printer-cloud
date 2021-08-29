//package com.xsg.common.printer.action;
//
//import com.xsg.common.printer.constants.VoucherEnum;
//import com.xsg.common.printer.model.PrintBatchParam;
//import com.xsg.common.printer.model.XsgTemplate;
//import com.xsg.common.printer.utils.*;
//import org.apache.commons.lang.StringUtils;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
///**
// * @author 高总辉
// * @desc
// * @date 2020/12/16 9:40 上午
// */
//public class VoucherProcessor {
//
//    /**
//     * 单个打印
//     * @param template
//     * @param voucherEnum
//     * @param param
//     * @return
//     */
//    public static String transport(XsgTemplate template, VoucherEnum voucherEnum, Object param) {
//        String result = VoucherHandler.print(template, voucherEnum, param);
//        if (StringUtils.isBlank(result)) {
//            return null;
//        }
//        switch (voucherEnum) {
//            case PDF:
//            case IMAGE:
//                //上传oss
//                if (OssMgrBean.getInstance() != null) {
//                    String ossUrl = OssMgrBean.getInstance().uploadFile(result);
//                    FileWriterUtils.clearFile(result);
//                    return ossUrl;
//                }
//                break;
//            case INSTRUCT_TSPL:
//            case INSTRUCT_TSPL_HPRT:
//            case INSTRUCT_CPCL:
//                return result;
//        }
//        return null;
//    }
//
//    /**
//     * 单个打印不上传
//     * @param template
//     * @param voucherEnum
//     * @param param
//     * @return
//     */
//    public static String transportNotUpload (XsgTemplate template, VoucherEnum voucherEnum, Object param) {
//        return VoucherHandler.print(template, voucherEnum, param);
//    }
//
//    /**
//     * 批量打印 单个模版
//     * @param template
//     * @param voucherEnum
//     * @param params
//     * @return
//     */
//    public static String transportBatch(XsgTemplate template, VoucherEnum voucherEnum, List<Object> params) {
//        if (params == null || params.isEmpty()) {
//            return null;
//        }
//        if (params.size() == 1) {
//            return transport(template, voucherEnum, params.get(0));
//        }
//        //多线程处理批量打印
////        ParallelThreadHandler<String> parallelThreadHandler = new ParallelTaskThreadPool<>("批量打印");
////        for (Object param : params) {
////            parallelThreadHandler.addTask(ParallelSupplierTask.newTask(() -> VoucherHandler.print(template, voucherEnum, param)));
////        }
////        parallelThreadHandler.run();
////        List<String> results = parallelThreadHandler.getResults();
//        List<String> results = new ArrayList<>();
//        for (Object param : params) {
//            results.add(VoucherHandler.print(template, voucherEnum, param));
//        }
//        return mergeResult(voucherEnum, results);
//    }
//
//    /**
//     * 多个模版， 同一打印类型批量打印,合并
//     * @param voucherEnum
//     * @param printBatchParams
//     * @return
//     */
//    public static String transportBatchBatch(VoucherEnum voucherEnum, List<PrintBatchParam<XsgTemplate>> printBatchParams) {
//        if (voucherEnum == null) {
//            return null;
//        }
//        if (printBatchParams == null || printBatchParams.isEmpty()) {
//            return null;
//        }
//        if (printBatchParams.size() == 1) {
//            PrintBatchParam<XsgTemplate> printBatchParam = printBatchParams.get(0);
//            return transportBatch(printBatchParam.getTemplate(), voucherEnum, printBatchParam.getParams());
//        }
//        List<String> results = new ArrayList<>();
//        for (PrintBatchParam<XsgTemplate> batchParam : printBatchParams) {
//            if (batchParam.getParams() == null || batchParam.getParams().isEmpty()) {
//                continue;
//            }
//            for (Object param : batchParam.getParams()) {
//                results.add(VoucherHandler.print(batchParam.getTemplate(), voucherEnum, param));
//            }
//        }
//        return mergeResult(voucherEnum, results);
//    }
//
//    /**
//     * 多个模版， 同一打印类型批量打印,不合并，不上传结果
//     * @param voucherEnum
//     * @param printBatchParams
//     * @return
//     */
//    public static List<String> transportBatchBatchNotMergeUpload (VoucherEnum voucherEnum, List<PrintBatchParam<XsgTemplate>> printBatchParams) {
//        if (voucherEnum == null) {
//            return null;
//        }
//        if (printBatchParams == null || printBatchParams.isEmpty()) {
//            return null;
//        }
//        List<String> results = new ArrayList<>();
//        for (PrintBatchParam<XsgTemplate> batchParam : printBatchParams) {
//            if (batchParam.getParams() == null || batchParam.getParams().isEmpty()) {
//                continue;
//            }
//            for (Object param : batchParam.getParams()) {
//                results.add(VoucherHandler.print(batchParam.getTemplate(), voucherEnum, param));
//            }
//        }
//        return results;
//    }
//
//    /**
//     * 合并批量打印结果
//     * @param voucherEnum
//     * @param results
//     * @return
//     */
//    private static String mergeResult (VoucherEnum voucherEnum, List<String> results) {
//        if (results == null || results.isEmpty()) {
//            return null;
//        }
//        switch (voucherEnum) {
//            //合并pdf
//            case PDF:
//                String destPdfPath = PathUtil.getPdfPath();
//                boolean isPdfMarge = PdfUtil.merge(results.toArray(new String[]{}), destPdfPath);
//                if (isPdfMarge) {
//                    //上传oss
//                    if (OssMgrBean.getInstance() != null) {
//                        String ossUrl = OssMgrBean.getInstance().uploadFile(destPdfPath);
//                        //清理
//                        results.add(destPdfPath);
//                        FileWriterUtils.clearFiles(results);
//                        return ossUrl;
//                    }
//                }
//                break;
//            case IMAGE:
//                String destPath = PathUtil.getPngPath();
//                boolean isMarge = ImageUtil.merge(results.toArray(new String[]{}), destPath);
//                if (isMarge) {
//                    //上传oss
//                    if (OssMgrBean.getInstance() != null) {
//                        String ossUrl = OssMgrBean.getInstance().uploadFile(destPath);
//                        //清理
//                        results.add(destPath);
//                        FileWriterUtils.clearFiles(results);
//                        return ossUrl;
//                    }
//                }
//                break;
//            case INSTRUCT_TSPL_HPRT:
//            case INSTRUCT_TSPL:
//                return results.stream().collect(Collectors.joining("\r\n"));
//            case INSTRUCT_CPCL:
//                return results.stream().collect(Collectors.joining("\n"));
//        }
//        return null;
//    }
//}
