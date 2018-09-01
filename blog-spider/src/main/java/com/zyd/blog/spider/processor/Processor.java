package com.zyd.blog.spider.processor;

import com.zyd.blog.spider.model.BaseModel;
import us.codecraft.webmagic.Page;

public interface Processor {

    void process(Page page, BaseModel model);
}
