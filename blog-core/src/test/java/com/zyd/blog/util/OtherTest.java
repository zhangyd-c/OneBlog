package com.zyd.blog.util;

import org.assertj.core.util.Arrays;
import org.junit.Test;

import java.io.File;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @date 2018/4/23 11:35
 * @since 1.0
 */
public class OtherTest {

    private String projectPath = "D:\\zhyd-project\\开源项目\\DBlog\\";
    private boolean ignoreFile = true;

    @Test
    public void dir() {
        String projectDir = "blog-core\\src\\main\\java";

        File file = new File(projectPath + projectDir);

        writeDir(file, -2);

    }

    private void writeDir(File file, int blankNum) {
        if (!file.isDirectory()) {
            printLog(blankNum, file);
            return;
        }
        File[] nodeFiles = file.listFiles();
        if (Arrays.isNullOrEmpty(nodeFiles)) {
            return;
        }
        orderFileList(nodeFiles);

        blankNum = blankNum + 2;
        for (File nodeFile : nodeFiles) {
            printLog(blankNum, nodeFile);
            if (nodeFile.isDirectory()) {
                writeDir(nodeFile, blankNum);
            }
        }
    }

    private void printLog(int blankNum, File file) {
        if (file.isFile() && ignoreFile) {
            return;
        }
        System.out.println(getBlank(blankNum) + formatPath(file));
    }

    private String getBlank(int blankNum) {
        StringBuilder blank = new StringBuilder("|");
        for (int i = 0; i < blankNum; i++) {
            blank.append("-");
        }
        return blank.toString();
    }

    private String formatPath(File file) {
        if (null == file) {
            return null;
        }
        String path = file.getAbsolutePath();
//        if (path.contains(projectPath)) {
//            path = path.replaceAll(projectPath.replace("\\", "\\\\"), "");
//        }
        path = path.replaceAll(file.getParent().replace("\\", "\\\\") + "\\\\", "");
        return path;
    }

    private void orderFileList(File[] files) {
        List fileList = java.util.Arrays.asList(files);
        Collections.sort(fileList, new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                if (o1.isDirectory() && o2.isFile()) {
                    return -1;
                }
                if (o1.isFile() && o2.isDirectory()) {
                    return 1;
                }
                return 0;
            }
        });
    }
}
