package com.zyd.blog.file;


import com.zyd.blog.file.alioss.api.OssApi;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class OSSTest {

    String endpoint = "改为自己的";
    String accessKeyId = "改为自己的";
    String accessKeySecret = "改为自己的";

    String newBucketName = "oneblog-bucket";

    @Test
    public void createBucket() {
        OssApi ossApi = new OssApi(endpoint, accessKeyId, accessKeySecret);

        ossApi.createBucket(newBucketName);
    }

    @Test
    public void uploadFile() {
        String filePath = "D:\\zhangyadong\\zyd-project\\文件\\公众号素材\\timg.jpg";

        OssApi ossApi = new OssApi(endpoint, accessKeyId, accessKeySecret);
        try {
            InputStream inputStream = new FileInputStream(filePath);
            String res = ossApi.uploadFile(inputStream, "test/asd/111111.png", newBucketName);
            System.out.println(res);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deleteFile() {
        OssApi ossApi = new OssApi(endpoint, accessKeyId, accessKeySecret);
        ossApi.deleteFile("test/asd/111111.png", newBucketName);
    }
}
