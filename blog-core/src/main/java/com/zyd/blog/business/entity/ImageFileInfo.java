/**
 * MIT License
 * Copyright (c) 2018 yadong.zhang
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.zyd.blog.business.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @website https://www.zhyd.me
 * @version 1.0
 * @date 2018/4/16 16:26
 * @since 1.0
 */
@Data
@EqualsAndHashCode
public class ImageFileInfo {
    public long size;
    public String type;
    public int width;
    public int height;
    public String filename;

    public ImageFileInfo() {
    }

    public ImageFileInfo(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public ImageFileInfo withSize(long size) {
        this.size = size;
        return this;
    }

    public ImageFileInfo withType(String type) {
        this.type = type;
        return this;
    }

    public ImageFileInfo withWidth(int width) {
        this.width = width;
        return this;
    }

    public ImageFileInfo withHeight(int height) {
        this.height = height;
        return this;
    }

    public ImageFileInfo withFilename(String filename) {
        this.filename = filename;
        return this;
    }
}
