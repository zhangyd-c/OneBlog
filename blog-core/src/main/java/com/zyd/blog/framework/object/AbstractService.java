package com.zyd.blog.framework.object;

import java.util.List;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @website https://www.zhyd.me
 * @version 1.0
 * @date 2018/4/16 16:26
 * @since 1.0
 */
public interface AbstractService<T, PK> {

    T insert(T entity);

    default void insertList(List<T> entities) {

    }

    boolean removeByPrimaryKey(PK primaryKey);

    default boolean update(T entity) {
        return false;
    }

    boolean updateSelective(T entity);

    T getByPrimaryKey(PK primaryKey);

    default T getOneByEntity(T entity) {
        return null;
    }

    default List<T> listAll() {
        return null;
    }

    default List<T> listByEntity(T entity) {
        return null;
    }
}