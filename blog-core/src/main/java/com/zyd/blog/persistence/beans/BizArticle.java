package com.zyd.blog.persistence.beans;

import com.zyd.blog.framework.object.AbstractDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Transient;
import java.util.List;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @website https://www.zhyd.me
 * @version 1.0
 * @date 2018/4/16 16:26
 * @since 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BizArticle extends AbstractDO {
    @Transient
    List<BizTags> tags;
    @Transient
    BizType bizType;
    private String title;
    private Long userId;
    private String coverImage;
    private String qrcodePath;
    private Boolean isMarkdown;
    private String content;
    private String contentMd;
    private Boolean top;
    private Long typeId;
    private Integer status;
    private Boolean recommended;
    private Boolean original;
    private String description;
    private String keywords;
    private Boolean comment;
    @Transient
    private Integer lookCount;
    @Transient
    private Integer commentCount;
    @Transient
    private Integer loveCount;
}
