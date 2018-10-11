package com.zyd.blog.spider.webmagic;

import com.zyd.blog.spider.enums.ExitWayEnum;
import com.zyd.blog.spider.model.BaseModel;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2018/9/26 17:21
 * @since 1.8
 */
public class ZhydSpider extends Spider {

    /**
     * 用来保存正在运行的所有Spider，key要求唯一，一般为用户ID，需要调用方生成
     */
    public static final ConcurrentHashMap<Long, ZhydSpider> SPIDER_BUCKET = new ConcurrentHashMap<>();

    private BaseModel model;

    /**
     * 唯一的key，一般为用户ID，需要调用方生成
     */
    private Long uuid;
    private volatile long startTime = 0L;

    private ZhydSpider(PageProcessor pageProcessor, BaseModel model, Long uuid) {
        super(pageProcessor);
        this.model = model;
        this.uuid = uuid;
        SPIDER_BUCKET.put(uuid, this);
    }

    public static ZhydSpider create(PageProcessor pageProcessor, BaseModel model, Long uuid) {
        return new ZhydSpider(pageProcessor, model, uuid);
    }

    @Override
    protected void onSuccess(Request request) {
        super.onSuccess(request);
        if (this.getStatus() == Spider.Status.Running && ExitWayEnum.DURATION.toString().equals(model.getExitWay())) {
            if (startTime < System.currentTimeMillis()) {
                this.stop();
            }
        }
    }

    @Override
    public void run() {
        startTime = System.currentTimeMillis() + model.getCount() * 1000;
        super.run();
    }

    @Override
    protected void onError(Request request) {
        super.onError(request);
    }

    @Override
    public void close() {
        super.close();
        SPIDER_BUCKET.remove(this.uuid);
    }

    @Override
    public void stop() {
        super.stop();
//        this.close();
        SPIDER_BUCKET.remove(this.uuid);
    }
}
