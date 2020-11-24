package com.itheima.czjk.service;

import com.itheima.czjk.Exception.MyException;
import com.itheima.czjk.entity.PageResult;
import com.itheima.czjk.entity.QueryPageBean;
import com.itheima.czjk.pojo.CheckGroup;

import java.util.List;

public interface CheckGroupService {
     void add(CheckGroup checkGroup, Integer[] checkitemIds);

    PageResult<CheckGroup> findPage(QueryPageBean queryPageBean);

    CheckGroup findById(int id);

    List<Integer> findCheckItemIdsByCheckGroupId(int id);

    void update(CheckGroup checkGroup, Integer[] checkitemIds);

    void delete(int id)throws MyException;

    List<CheckGroup> findAll();
}
