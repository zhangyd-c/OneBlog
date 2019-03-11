package com.zyd.blog;

import com.zyd.blog.business.enums.FileUploadType;
import com.zyd.blog.file.FileUploader;
import com.zyd.blog.file.entity.VirtualFile;
import com.zyd.blog.plugin.file.GlobalFileUploader;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class BlogAdminApplicationTests {

    @Test
    public void uploadFile() {
        FileUploader uploader = new GlobalFileUploader();
        File file = new File("C:\\Users\\yadon\\Desktop\\新建文件夹\\web-index-pc.png");
        VirtualFile virtualFile = uploader.upload(file, FileUploadType.SIMPLE.getPath(), true);
        System.out.println(virtualFile);
    }
}
