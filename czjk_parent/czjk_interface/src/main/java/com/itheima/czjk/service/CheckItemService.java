package com.itheima.czjk.service;

import com.itheima.czjk.Exception.MyException;
import com.itheima.czjk.entity.PageResult;
import com.itheima.czjk.entity.QueryPageBean;
import com.itheima.czjk.pojo.CheckItem;

import java.util.List;

public interface CheckItemService {
    List<CheckItem> findAll();

    void add(CheckItem checkitem);

    PageResult<CheckItem> findPage(QueryPageBean queryPageBean);

    CheckItem findById(int id);

    void updateItem(CheckItem checkitem);

    void deleteById(int id) throws MyException;

}
