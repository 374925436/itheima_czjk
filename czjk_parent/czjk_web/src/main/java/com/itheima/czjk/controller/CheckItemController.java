package com.itheima.czjk.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.czjk.constant.MessageConstant;
import com.itheima.czjk.entity.PageResult;
import com.itheima.czjk.entity.QueryPageBean;
import com.itheima.czjk.entity.Result;
import com.itheima.czjk.pojo.CheckItem;
import com.itheima.czjk.service.CheckItemService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/checkitem")
public class CheckItemController {
    @Reference
    private CheckItemService checkItemService;
    @RequestMapping("/findAll")
    public Result findAll(){
          List<CheckItem> checkItemList= checkItemService.findAll();
        return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS,checkItemList);
    }
    @RequestMapping("/addItem")
    public Result addItem(@RequestBody CheckItem checkitem){
       checkItemService.add(checkitem);
       return new Result(true, MessageConstant.ADD_CHECKITEM_SUCCESS);
    }
    @RequestMapping("/findPage")
    public Result findPage(@RequestBody QueryPageBean queryPageBean){
    PageResult<CheckItem> pageResult= checkItemService.findPage(queryPageBean);
    return new Result(true,MessageConstant.QUERY_CHECKITEM_SUCCESS,pageResult);
}
    @RequestMapping("/findById")
    public Result findById(int id){
        CheckItem checkItem= checkItemService.findById(id);
        return new Result(true,MessageConstant.QUERY_CHECKITEM_SUCCESS,checkItem);
    }
    @RequestMapping("/updateItem")
    public Result updateItem(@RequestBody CheckItem checkitem){
        checkItemService.updateItem(checkitem);
        return new Result(true, MessageConstant.EDIT_CHECKITEM_SUCCESS);
    }
    @RequestMapping("/deleteById")
    public Result deleteById(int id){
        checkItemService.deleteById(id);
        return new Result(true, MessageConstant.DELETE_CHECKITEM_SUCCESS);
    }
}
