package com.zyd.blog.util;

import org.junit.Test;

/**
 * 密码加密测试工具类
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://docs.zhyd.me
 * @date 2018/4/18 11:48
 * @since 1.0
 */
public class PasswordUtilTest {

    @Test
    public void passwordTest() throws Exception {
        encryptTest("123456", "admin");
    }

    public void encryptTest(String password, String salt) throws Exception {
        String encrypt = PasswordUtil.encrypt(password, salt);
        System.out.println(encrypt);
        String decrypt = PasswordUtil.decrypt("CGUx1FN++xS+4wNDFeN6DA==", "root");
        System.out.println(decrypt);
    }

}
