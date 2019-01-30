package com.zyd.blog.spider;

import com.zyd.blog.spider.enums.ExitWayEnum;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2018/9/27 17:26
 * @since 1.8
 */
public class OtherTest {

    @Test
    public void main() {
        Map<String, Function> map = new HashMap<>();
        map.put(ExitWayEnum.URL_COUNT.toString(), (count) -> String.format("当前程序将会在抓取完%s个连接后停止...", count));
        map.put(ExitWayEnum.DURATION.toString(), (count) -> String.format("当前程序将会在运行%s秒后停止...", count));

        System.out.println(map.get(ExitWayEnum.DURATION.toString()).apply(100));
    }

    @Test
    public void enumTest(){
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            EnumTest.TWO.toString();
        }
        long end = System.currentTimeMillis();
        System.out.println("enum.toString  --> " + (end - start));
        for (int i = 0; i < 10000000; i++) {
            EnumTest.valueOf("TWO");
        }
        System.out.println("enum.valueOf  --> " + (System.currentTimeMillis() - end));
    }

    private enum EnumTest {
        ONE,
        TWO,
        THREE
    }

    @Test
    public void checkProxy() throws IOException {
        //http://1212.ip138.com/ic.asp 可以换成任何比较快的网页
        Connection.Response response = Jsoup.connect("http://ip138.com/ips138.asp")
                .timeout(10 * 1000)
                .proxy("61.135.217.7", 80)
                .execute();
        System.out.println(response.statusCode());
    }
}
