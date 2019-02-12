package com.zyd.blog.business.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zyd.blog.persistence.beans.BizFile;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author innodev java team
 * @version 1.0
 * @date 2018/12/14 09:23
 * @since 1.8
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class File {
    private BizFile file;

    public File(BizFile file) {
        this.file = file;
    }

    public File() {
    }

    @JsonIgnore
    public BizFile getFile() {
        return file;
    }

    public File setFile(BizFile file) {
        this.file = file;
        return this;
    }

    public Long getId() {
        return this.file.getId();
    }

    public File setId(Long id) {
        this.file.setId(id);
        return this;
    }

    public Long getUserId() {
        return this.file.getUserId();
    }

    public File setUserId(Long userId) {
        this.file.setUserId(userId);
        return this;
    }

    public String getOriginalFileName() {
        return this.file.getOriginalFileName();
    }

    public File setOriginalFileName(String originalFileName) {
        this.file.setOriginalFileName(originalFileName);
        return this;
    }

    public String getFilePath() {
        return this.file.getFilePath();
    }

    public File setFilePath(String filePath) {
        this.file.setFilePath(filePath);
        return this;
    }

    public String getFullFilePath() {
        return this.file.getFullFilePath();
    }

    public File setFullFilePath(String fullFilePath) {
        this.file.setFullFilePath(fullFilePath);
        return this;
    }

    public String getFileHash() {
        return this.file.getFileHash();
    }

    public File setFileHash(String fileHash) {
        this.file.setFileHash(fileHash);
        return this;
    }

    public String getUploadType() {
        return this.file.getUploadType();
    }

    public File setUploadType(String uploadType) {
        this.file.setUploadType(uploadType);
        return this;
    }

    public Date getUploadStartTime() {
        return this.file.getUploadStartTime();
    }

    public File setUploadStartTime(Date uploadStartTime) {
        this.file.setUploadStartTime(uploadStartTime);
        return this;
    }

    public Date getUploadEndTime() {
        return this.file.getUploadEndTime();
    }

    public File setUploadEndTime(Date uploadEndTime) {
        this.file.setUploadEndTime(uploadEndTime);
        return this;
    }

    public Date getCreateTime() {
        return this.file.getCreateTime();
    }

    public File setCreateTime(Date createTime) {
        this.file.setCreateTime(createTime);
        return this;
    }

    public Date getUpdateTime() {
        return this.file.getUpdateTime();
    }

    public File setUpdateTime(Date updateTime) {
        this.file.setUpdateTime(updateTime);
        return this;
    }

    public Long getSize() {
        return this.file.getSize();
    }

    public File setSize(Long size) {
        this.file.setSize(size);
        return this;
    }

    public String getSuffix() {
        return this.file.getSuffix();
    }

    public File setSuffix(String suffix) {
        this.file.setSuffix(suffix);
        return this;
    }

    public Integer getWidth() {
        return this.file.getWidth();
    }

    public File setWidth(Integer width) {
        this.file.setWidth(width);
        return this;
    }

    public Integer getHeight() {
        return this.file.getHeight();
    }

    public File setHeight(Integer height) {
        this.file.setHeight(height);
        return this;
    }

    public String getStorageType() {
        return this.file.getStorageType();
    }

    public File setStorageType(String storageTypeEnum) {
        this.file.setStorageType(storageTypeEnum);
        return this;
    }

}