package com.itheima.czjk.service;

import com.itheima.czjk.Exception.MyException;
import com.itheima.czjk.entity.PageResult;
import com.itheima.czjk.entity.QueryPageBean;
import com.itheima.czjk.pojo.Setmeal;

public interface SetMealService {
    PageResult<Setmeal> fingPage(QueryPageBean queryPageBean);

    Setmeal findById(int id);

    Integer[] findCheckGroupById(int id);

    void deleteById(int id) throws MyException;

    void addSetMeal(Setmeal setmeal, Integer[] id);

    void update(Setmeal setmeal, Integer[] id);
}
