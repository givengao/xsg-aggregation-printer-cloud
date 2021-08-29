package com.xsg.common.printer.utils;

import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class FileWriterUtils {

    private static final Logger logger = LoggerFactory.getLogger(FileWriterUtils.class);

    /**
     * 清理文件
     * @param path
     * @return
     */
    public static void clearFile (String path) {
        if (path == null) {
            return;
        }
        File file = new File(path);
        clearFile(file);
    }

    /**
     * 清理文件
     * @param paths
     */
    public static void clearFiles (List<String> paths) {
        if (paths == null || paths.isEmpty()) {
            return;
        }
        for (String path : paths) {
            clearFile(path);
        }
    }

    /**
     * 清空文件
     * @param file
     */
    public static void clearFile(File file) {
        if (file == null || !file.exists()) {
            return;
        }
        if (file.isFile() || file.list() == null) {
            try {
                Files.delete(file.toPath());
            } catch (IOException e) {
                logger.error("清空文件失败 ->{}, e->{}", file.getName(), e);
            }
        } else {
            for (File f : file.listFiles()) {
                clearFile(f);
            }
            try {
                Files.delete(file.toPath());
            } catch (IOException e) {
                logger.error("清空文件夹失败 ->{}, e->{}", file.getName(), e);
            }
        }
    }

    /**
     *
     * @Description: 创建文件
     * @param
     * @return
     * @author: hongcheng.wu
     * @date: 2020/5/7 15:59
     */
    @SneakyThrows
    public static File createIfNotExists(final File file) {
        if (file == null) {
            return null;
        }
        if (file.exists()) {
            return file;
        }

        final File parent = file.getParentFile();
        if (!parent.exists()) {
            parent.mkdirs();
        }
        if (!file.createNewFile()) {
            logger.error("创建文件失败");
            return null;
        }
        return file;
    }
}
