package com.itheima.czjk.dao;

import com.github.pagehelper.Page;
import com.itheima.czjk.pojo.CheckGroup;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CheckGroupDao {
    void add(CheckGroup checkGroup);
    void addCheckGroupCheckItem(@Param("checkitemId") Integer checkitemId,@Param("id") Integer id);

    Page<CheckGroup> findPage(String queryString);

    CheckGroup findById(int id);

    List<Integer> findCheckItemIdsByCheckGroupId(int id);

    void delete(Integer id);

    void update(CheckGroup checkGroup);

    Integer isFindById(int id);

    void deleteById(int id);

    List<CheckGroup> findAll();
}
