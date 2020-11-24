package com.itheima.czjk.dao;

import com.github.pagehelper.Page;
import com.itheima.czjk.Exception.MyException;
import com.itheima.czjk.pojo.Setmeal;
import org.apache.ibatis.annotations.Param;

public interface SetMealDao {
    Page<Setmeal> findPage(String queryString);
    Setmeal findById(int id);

    Integer[] findCheckGroupById(int id);

    int findOrderCountBySetmealId(int id) throws MyException;

    void deleteById(int id);

    void addSetMeal(Setmeal setmeal);

    void addCheckGroupSetmealdao(@Param("checkGroupId") Integer checkGroupId,@Param("setmealId")Integer setmealId);

    void deleteSetmealCheckGroup(int id);

    void update(Setmeal setmeal);
}
