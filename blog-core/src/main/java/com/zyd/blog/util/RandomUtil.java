package com.zyd.blog.util;

import java.util.Random;

/**
 * 随机字符串
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2018/4/18 11:48
 * @since 1.0
 */
public class RandomUtil {

    private static final char[] CHAR_NUMBER_CODE_SEQUENCE = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',
            'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    private static final char[] CHAR_CODE_SEQUENCE = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',
            'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    private static final char[] NUMBER_CODE_SEQUENCE = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    /**
     * 随机字符串和数字
     *
     * @param length
     *         字符长度
     * @return
     */
    public static String random(int length) {
        int codeSequenceLength = CHAR_NUMBER_CODE_SEQUENCE.length;
        Random random = new Random();
        StringBuffer randomStr = new StringBuffer();
        for (int i = 0; i < length; i++) {
            randomStr.append(String.valueOf(CHAR_NUMBER_CODE_SEQUENCE[random.nextInt(codeSequenceLength - 1)]));
        }
        return randomStr.toString();
    }

    /**
     * 随机纯字符串
     *
     * @param length
     *         字符长度
     * @return
     */
    public static String randomStr(int length) {
        int codeSequenceLength = CHAR_CODE_SEQUENCE.length;
        Random random = new Random();
        StringBuffer randomStr = new StringBuffer();
        for (int i = 0; i < length; i++) {
            randomStr.append(String.valueOf(CHAR_CODE_SEQUENCE[random.nextInt(codeSequenceLength - 1)]));
        }
        return randomStr.toString();
    }

    /**
     * 随机数字
     *
     * @param length
     *         字符长度
     * @return
     */
    public static String randomNum(int length) {
        int codeSequenceLength = NUMBER_CODE_SEQUENCE.length;
        Random random = new Random();
        StringBuffer randomStr = new StringBuffer();
        for (int i = 0; i < length; i++) {
            randomStr.append(String.valueOf(NUMBER_CODE_SEQUENCE[random.nextInt(codeSequenceLength - 1)]));
        }
        return randomStr.toString();
    }
}
