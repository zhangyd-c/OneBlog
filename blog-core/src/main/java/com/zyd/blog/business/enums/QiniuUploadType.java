package com.zyd.blog.business.enums;

import com.zyd.blog.business.consts.FileConst;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @website https://www.zhyd.me
 * @version 1.0
 * @date 2018/4/16 16:26
 * @since 1.0
 */
public enum QiniuUploadType {
    QRCODE("zhyd/qrcode/", FileConst.DEFAULT_IMG_WIDTH, FileConst.DEFAULT_IMG_HEIGHT, FileConst.DEFAULT_IMG_SIZE),
    SIMPLE("zhyd/article/", FileConst.DEFAULT_IMG_WIDTH, FileConst.DEFAULT_IMG_HEIGHT, FileConst.DEFAULT_IMG_SIZE),
    COVER_IMAGE("zhyd/cover/", FileConst.DEFAULT_IMG_WIDTH, FileConst.DEFAULT_IMG_HEIGHT, FileConst.DEFAULT_IMG_SIZE);

    private String path;
    /**
     * 上传图片的宽度
     */
    private int[] width;
    /**
     * 上传图片的高度
     */
    private int[] height;
    /**
     * 上传图片的大小
     */
    private int[] size;

    QiniuUploadType(String path, int[] width, int[] height, int[] size) {
        this.path = path;
        this.width = width;
        this.height = height;
        this.size = size;
    }

    public String getPath() {
        return path;
    }

    public int[] getWidth() {
        return width;
    }

    public int[] getHeight() {
        return height;
    }

    public int[] getSize() {
        return size;
    }
}
