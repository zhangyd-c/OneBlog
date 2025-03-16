package com.zyd.blog.business.dto;

import com.zyd.blog.persistence.beans.BizArticleLookV2;
import lombok.Data;

@Data
public class ArticleLookDtoV2 extends BizArticleLookV2 {
    private String articleName;
}
