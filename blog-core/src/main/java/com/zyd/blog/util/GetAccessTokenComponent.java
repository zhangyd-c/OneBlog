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
 * @Createdate: 2022/8/4 13:58
 */
@Component
public class GetAccessTokenComponent {

    /**
     * 网页授权接口
     */
    public final String AccessTokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=SECRET";

    public Map<String, String> getAccessToken(String appId, String AppSecret) {
        Map<String, String> result = new HashMap<>();

        String requestUrl = AccessTokenUrl.replace("APPID", appId).replace("SECRET", AppSecret);
        HttpClient client = null;

        try {
            client = new DefaultHttpClient();
            HttpGet httpget = new HttpGet(requestUrl);
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            String response = client.execute(httpget, responseHandler);
            JSONObject OpenidJSON = JSONObject.parseObject(response);

            String accessToken = String.valueOf(OpenidJSON.get("access_token"));
            // 秒
            Integer expiresIn = (Integer) OpenidJSON.get("expires_in");

            result.put("accessToken", accessToken);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            client.getConnectionManager().shutdown();
        }
        return result;
    }
}
