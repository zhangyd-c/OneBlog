package com.zyd.blog.file;

import com.zyd.blog.file.entity.VirtualFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://docs.zhyd.me
 * @date 2019/2/11 13:29
 * @since 1.8
 */
public interface FileUploader {

    /**
     * 上传文件
     *
     * @param file       待上传的文件流
     * @param uploadType 文件上传类型，用来区分文件
     * @param suffix     文件后缀
     * @param save       是否保存
     */
    VirtualFile upload(InputStream file, String uploadType, String suffix, boolean save);

    /**
     * 上传文件
     *
     * @param file       待上传的文件
     * @param uploadType 文件上传类型，用来区分文件
     * @param save       是否保存
     */
    VirtualFile upload(File file, String uploadType, boolean save);

    /**
     * 上传文件
     *
     * @param file       待上传的文件
     * @param uploadType 文件上传类型，用来区分文件
     * @param save       是否保存
     */
    VirtualFile upload(MultipartFile file, String uploadType, boolean save);

    /**
     * 删除文件
     *
     * @param filePath   文件路径
     * @param uploadType 文件类型
     */
    boolean delete(String filePath, String uploadType);
}
