package com.zyd.blog.business.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zyd.blog.business.entity.File;
import com.zyd.blog.business.enums.FileUploadType;
import com.zyd.blog.business.service.BizFileService;
import com.zyd.blog.business.vo.FileConditionVO;
import com.zyd.blog.file.FileUploader;
import com.zyd.blog.file.exception.GlobalFileException;
import com.zyd.blog.persistence.beans.BizFile;
import com.zyd.blog.persistence.mapper.BizFileMapper;
import com.zyd.blog.plugin.file.GlobalFileUploader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author innodev java team
 * @version 1.0
 * @date 2018/12/14 09:23
 * @since 1.8
 */
@Service
public class BizFileServiceImpl implements BizFileService {

    @Autowired
    private BizFileMapper shopFileMapper;

    @Override
    public PageInfo<File> findPageBreakByCondition(FileConditionVO vo) {
        PageHelper.startPage(vo.getPageNumber(), vo.getPageSize());
        List<BizFile> list = shopFileMapper.findPageBreakByCondition(vo);
        List<File> boList = getFiles(list);
        if (boList == null) return null;
        PageInfo bean = new PageInfo<BizFile>(list);
        bean.setList(boList);
        return bean;
    }

    private List<File> getFiles(List<BizFile> list) {
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        List<File> boList = new ArrayList<>();
        for (BizFile bizFile : list) {
            boList.add(new File(bizFile));
        }
        return boList;
    }

    @Override
    public File selectFileByPathAndUploadType(String filePath, String uploadType) {
        if (StringUtils.isEmpty(filePath)) {
            return null;
        }
        BizFile file = new BizFile();
        file.setFilePath(filePath);
        if (StringUtils.isEmpty(uploadType)) {
            file.setUploadType(uploadType);
        }
        List<BizFile> fileList = shopFileMapper.select(file);
        return CollectionUtils.isEmpty(fileList) ? null : new File(fileList.get(0));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void remove(Long[] ids) {
        for (Long id : ids) {
            File oldFile = this.getByPrimaryKey(id);
            this.removeByPrimaryKey(id);
            try {
                FileUploader uploader = new GlobalFileUploader();
                uploader.delete(oldFile.getFilePath(), oldFile.getUploadType());
            } catch (Exception ignored) {
            }
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int upload(MultipartFile[] file) {
        if (null == file || file.length == 0) {
            throw new GlobalFileException("请至少选择一张图片！");
        }
        for (MultipartFile multipartFile : file) {
            FileUploader uploader = new GlobalFileUploader();
            uploader.upload(multipartFile, FileUploadType.COMMON.getPath(), true);
        }
        return file.length;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public File insert(File entity) {
        Assert.notNull(entity, "Invalid parameter");
        entity.setCreateTime(new Date());
        entity.setUpdateTime(new Date());
        shopFileMapper.insertSelective(entity.getFile());
        return entity;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeByPrimaryKey(Long primaryKey) {
        Assert.notNull(primaryKey, "Invalid parameter");
        return shopFileMapper.deleteByPrimaryKey(primaryKey) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateSelective(File entity) {
        Assert.notNull(entity, "Invalid parameter");
        entity.setUpdateTime(new Date());
        return shopFileMapper.updateByPrimaryKeySelective(entity.getFile()) > 0;
    }

    @Override
    public File getByPrimaryKey(Long primaryKey) {
        Assert.notNull(primaryKey, "Invalid parameter");
        BizFile entity = shopFileMapper.selectByPrimaryKey(primaryKey);
        return new File(entity);
    }

    @Override
    public List<File> listAll() {
        List<BizFile> list = shopFileMapper.selectAll();

        return getFiles(list);
    }
}
