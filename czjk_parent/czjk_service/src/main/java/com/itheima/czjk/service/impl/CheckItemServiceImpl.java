package com.itheima.czjk.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.StringUtil;
import com.itheima.czjk.Exception.MyException;
import com.itheima.czjk.dao.CheckItemDao;
import com.itheima.czjk.entity.PageResult;
import com.itheima.czjk.entity.QueryPageBean;
import com.itheima.czjk.pojo.CheckItem;
import com.itheima.czjk.service.CheckItemService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
@Service(interfaceClass = CheckItemService.class)
public class CheckItemServiceImpl implements CheckItemService {
    @Autowired
    private CheckItemDao checkItemDao;
    @Override
    public List<CheckItem> findAll() {

        return checkItemDao.findAll();
    }

    @Override
    public void add(CheckItem checkitem) {
        checkItemDao.addItem(checkitem);
    }

    @Override
    public PageResult<CheckItem> findPage(QueryPageBean queryPageBean) {
        PageHelper.startPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());
        if (!StringUtil.isEmpty(queryPageBean.getQueryString())){
            queryPageBean.setQueryString("%"+queryPageBean.getQueryString()+"%");
        }
        Page<CheckItem> page =checkItemDao.findByCondition(queryPageBean.getQueryString());
        PageResult<CheckItem> pageResult=new PageResult<CheckItem>(page.getTotal(),page.getResult());
        return pageResult;
    }

    @Override
    public CheckItem findById(int id) {
        return checkItemDao.findById(id);
    }

    @Override
    public void updateItem(CheckItem checkitem) {
        checkItemDao.updateItem(checkitem);
    }

    @Override
    public void deleteById(int id) {
      int count=checkItemDao.findCountByCheckItemId(id);
      if (count>0){
          throw new MyException("该检查项被检查组使用了，不能删除");
      }
      checkItemDao.deleteById(id);
    }
}
