package com.itheima.czjk.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.StringUtil;
import com.itheima.czjk.Exception.MyException;
import com.itheima.czjk.dao.SetMealDao;
import com.itheima.czjk.entity.PageResult;
import com.itheima.czjk.entity.QueryPageBean;
import com.itheima.czjk.pojo.Setmeal;
import com.itheima.czjk.service.SetMealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Service(interfaceClass = SetMealService.class)
public class SetMealServiceImpl implements SetMealService {
    @Autowired
    private SetMealDao setMealDao;
    @Override
    public PageResult<Setmeal> fingPage(QueryPageBean queryPageBean) {
        PageHelper.startPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());
        if (!StringUtil.isEmpty(queryPageBean.getQueryString())){
            queryPageBean.setQueryString("%"+queryPageBean.getQueryString()+"%");
        }
   Page<Setmeal> page=setMealDao.findPage(queryPageBean.getQueryString());
        PageResult<Setmeal> pageResult=new PageResult<>(page.getTotal(),page.getResult());
        return pageResult;
    }

    @Override
    public Setmeal findById(int id) {
     Setmeal setmeal= setMealDao.findById(id);
        return setmeal;
    }

    @Override
    public Integer[] findCheckGroupById(int id) {
     Integer[] ids=setMealDao.findCheckGroupById(id);
        return ids;
    }

    @Override
    public void deleteById(int id) {
        int count=setMealDao.findOrderCountBySetmealId(id);
        if (count>0){
            throw new MyException("该项已绑定，请勿删除");
        }
        setMealDao.deleteSetmealCheckGroup(id);
        setMealDao.deleteById(id);
    }

    @Override
    @Transactional
    public void addSetMeal(Setmeal setmeal, Integer[] checkgroupIds) {
        setMealDao.addSetMeal(setmeal);
        Integer setmealId=setmeal.getId();
        for (Integer checkGroupId : checkgroupIds) {
            setMealDao.addCheckGroupSetmealdao(checkGroupId,setmealId);
        }
    }

    @Override
    public void update(Setmeal setmeal, Integer[] ids) {
        setMealDao.update(setmeal);
        setMealDao.deleteSetmealCheckGroup(setmeal.getId());
        for (Integer id : ids) {
            setMealDao.addCheckGroupSetmealdao(id,setmeal.getId());
        }
    }
}
