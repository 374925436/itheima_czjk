package com.itheima.czjk.dao;

import com.github.pagehelper.Page;
import com.itheima.czjk.pojo.CheckItem;

import java.util.List;

public interface CheckItemDao {
    List<CheckItem> findAll();

    void addItem(CheckItem checkitem);

    Page<CheckItem> findByCondition(String queryString);
    CheckItem findById(int id);

    void updateItem(CheckItem checkitem);

    int findCountByCheckItemId(int id);

    void deleteById(int id);
}
