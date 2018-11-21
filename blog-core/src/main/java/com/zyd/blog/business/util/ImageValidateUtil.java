package com.zyd.blog.business.util;

import com.zyd.blog.business.entity.ImageFileInfo;
import com.zyd.blog.business.enums.QiniuUploadType;
import com.zyd.blog.framework.exception.ZhydFileException;
import com.zyd.blog.util.ImageUtil;
import org.springframework.web.multipart.MultipartFile;

/**
 * 图片验证工具类
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2018/4/16 16:26
 * @since 1.0
 */
public class ImageValidateUtil {

    /**
     * 校验待上传的文件
     *
     * @param file
     *         待上传的文件
     * @param type
     *         文件类型
     */
    public static void validate(MultipartFile file, QiniuUploadType type) throws ZhydFileException {
        ImageFileInfo imgInfo = ImageUtil.getInfo(file);
        if (null == imgInfo) {
            throw new ZhydFileException("图片文件不可为空！");
        }
        int[] width = type.getWidth();
        int[] height = type.getHeight();
        int[] size = type.getSize();
        if (isEmptyArr(width) || isEmptyArr(height) || isEmptyArr(size)) {
            throw new ZhydFileException("图片校验规则配置不正确！");
        }
        if (checkValue(width, imgInfo.getWidth()) || checkValue(height, imgInfo.getHeight()) || checkValue(size, (int) imgInfo.getSize())) {
            throw new ZhydFileException("图片尺寸不正确，请严格按照提示上传文件！");
        }
    }

    private static boolean checkValue(int[] array, int value) {
        if (array.length > 1) {
            // 上传的文件尺寸为范围值，则比较最大和最小值
            return value < array[0] || value > array[1];
        } else {
            return value != array[0];
        }
    }

    private static boolean isEmptyArr(int[] array) {
        return array == null || array.length == 0;
    }
}
