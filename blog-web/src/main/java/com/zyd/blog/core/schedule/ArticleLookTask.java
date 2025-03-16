package com.zyd.blog.core.schedule;

import com.zyd.blog.business.entity.ArticleLook;
import com.zyd.blog.business.service.BizArticleLookService;
import com.zyd.blog.business.service.BizArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://docs.zhyd.me
 * @date 2019/3/21 17:53
 * @since 1.8
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class ArticleLookTask {

    private final BizArticleService bizArticleService;

    private final BizArticleLookService articleLookService;

    private final BlockingQueue<Long> queue = new ArrayBlockingQueue<>(1024);

    /**
     * 保存文章的浏览记录，先进先出
     */
    public void addLookRecordToQueue(Long articleId) {
        if (null == articleId) {
            return;
        }
        queue.offer(articleId);
    }

    public void save() {
        List<Long> bufferList = new ArrayList<>();
        while (true) {
            try {
                bufferList.add(queue.take());
                for (Long articleId : bufferList) {
                    if (!bizArticleService.isExist(articleId)) {
                        log.warn("{}-该文章不存在！", articleId);
                        continue;
                    }
                    articleLookService.increment(articleId);
                }
            } catch (InterruptedException e) {
                log.error("保存文章浏览记录失败--->[{}]", e.getMessage());
                // 防止缓冲队列填充数据出现异常时不断刷屏
                try {
                    Thread.sleep(1000);
                } catch (Exception err) {
                    log.error(err.getMessage());
                }
            } finally {
                bufferList.clear();
            }
        }
    }

}
