package com.zyd.blog.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: gelin
 * @phone：17788767989
 * @Createdate: 2022/8/4 14:00
 */
@Component
public class JsApiTicketComponent {

    /**
     * 网页授权接口
     */
    public final String AccessTokenUrl = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";

    public Map<String, String> JsapiTicket(String accessToken) {
        Map<String, String> result = new HashMap<>();

        // 缓存过期，重新生成
        String requestUrl = AccessTokenUrl.replace("ACCESS_TOKEN", accessToken);
        HttpClient client = null;

        try {
            client = new DefaultHttpClient();
            HttpGet httpget = new HttpGet(requestUrl);
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            String response = client.execute(httpget, responseHandler);
            JSONObject openidJSON = JSONObject.parseObject(response);
            String errcode = String.valueOf(openidJSON.get("errcode"));
            String errmsg = String.valueOf(openidJSON.get("errmsg"));
            String ticket = String.valueOf(openidJSON.get("ticket"));
            Integer expiresIn = (Integer) openidJSON.get("expires_in");

            result.put("errcode", errcode);
            result.put("errmsg", errmsg);
            result.put("ticket", ticket);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            client.getConnectionManager().shutdown();
        }
        return result;
    }
}
