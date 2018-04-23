/**
 * MIT License
 *
 * Copyright (c) 2018 yadong.zhang
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
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
