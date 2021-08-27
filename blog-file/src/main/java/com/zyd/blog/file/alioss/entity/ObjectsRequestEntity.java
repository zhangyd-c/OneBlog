package com.zyd.blog.file.alioss.entity;

import lombok.Data;

/**
 * https://help.aliyun.com/document_detail/32015.html?spm=5176.doc32021.6.665.PqGkRT
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://docs.zhyd.me
 * @date 2017/7/12 10:29
 * @since 1.8
 */
@Data
public class ObjectsRequestEntity {

    /*
     * 限定返回的object key必须以prefix作为前缀
     */
    private String prefix;
    /*
     * 设定结果从marker之后按字母排序的第一个开始返回
     */
    private String marker;
    /*
     * 限定此次返回object的最大数，如果不设定，默认为100，max-keys取值不能大于1000
     */
    private Integer maxKeys;
    /*
     * 是一个用于对Object名字进行分组的字符。
     * 所有名字包含指定的前缀且第一次出现delimiter字符之间的object作为一组元素——CommonPrefixes
     */
    private String delimiter;
    /*
     * 请求响应体中Object名称采用的编码方式，目前支持url。
     */
    private String encodingType;

    public ObjectsRequestEntity setPrefix(String prefix) {
        this.prefix = prefix;
        return this;
    }

    public ObjectsRequestEntity setMarker(String marker) {
        this.marker = marker;
        return this;
    }

    public ObjectsRequestEntity setMaxKeys(Integer maxKeys) {
        this.maxKeys = maxKeys;
        return this;
    }

    public ObjectsRequestEntity setDelimiter(String delimiter) {
        this.delimiter = delimiter;
        return this;
    }

    public ObjectsRequestEntity setEncodingType(String encodingType) {
        this.encodingType = encodingType;
        return this;
    }
}
