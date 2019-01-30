package com.zyd.blog.util;

import com.zyd.blog.business.entity.ImageFileInfo;
import com.zyd.blog.framework.exception.ZhydFileException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;

/**
 * 操作图片工具类
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2018/4/18 11:48
 * @since 1.0
 */
@Slf4j
public class ImageUtil {

    /**
     * 获取图片信息
     *
     * @param file
     * @throws IOException
     */
    public static ImageFileInfo getInfo(File file) {
        if (null == file) {
            return null;
        }
        try {
            return getInfo(new FileInputStream(file))
                    .withSize(file.length())
                    .withFilename(file.getName())
                    .withType(FileUtil.getSuffix(file.getName()));
        } catch (Exception e) {
            e.printStackTrace();
            throw new ZhydFileException("获取图片信息发生异常！", e);
        }
    }

    /**
     * 获取图片信息
     *
     * @param multipartFile
     * @throws IOException
     */
    public static ImageFileInfo getInfo(MultipartFile multipartFile) {
        if (null == multipartFile) {
            return null;
        }
        try {
            return getInfo(multipartFile.getInputStream())
                    .withSize(multipartFile.getSize())
                    .withFilename(multipartFile.getOriginalFilename())
                    .withType(FileUtil.getSuffix(multipartFile.getOriginalFilename()));
        } catch (Exception e) {
            e.printStackTrace();
            throw new ZhydFileException("获取图片信息发生异常！", e);
        }
    }

    /**
     * 获取图片信息
     *
     * @param inputStream
     * @throws IOException
     */
    public static ImageFileInfo getInfo(InputStream inputStream) {
        try (BufferedInputStream in = new BufferedInputStream(inputStream)) {
            //字节流转图片对象
            Image bi = ImageIO.read(in);
            //获取默认图像的高度，宽度
            return new ImageFileInfo(bi.getWidth(null), bi.getHeight(null));
        } catch (Exception e) {
            log.error("获取图片信息失败", e);
        }
        return new ImageFileInfo();
    }
}
