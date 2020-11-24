package com.itheima.czjk.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.czjk.constant.MessageConstant;
import com.itheima.czjk.entity.PageResult;
import com.itheima.czjk.entity.QueryPageBean;
import com.itheima.czjk.entity.Result;
import com.itheima.czjk.pojo.CheckGroup;
import com.itheima.czjk.service.CheckGroupService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
@RequestMapping("/checkgroup")
public class CheckGroupController {
    @Reference
    private CheckGroupService checkGroupService;
    @RequestMapping("/add")
    public Result add(@RequestBody CheckGroup checkGroup, Integer[] checkitemIds){
        checkGroupService.add(checkGroup,checkitemIds);
        return new Result(true,MessageConstant.ADD_CHECKGROUP_SUCCESS);
    }
    @RequestMapping("/findPage")
    public Result findPage(@RequestBody QueryPageBean queryPageBean){
       PageResult<CheckGroup> pageResult =checkGroupService.findPage(queryPageBean);
        return new Result(true,"查询分页成功",pageResult);
    }
    @RequestMapping("/findAll")
    public Result findAll(){
        List<CheckGroup> checkGroups =checkGroupService.findAll();
        return new Result(true,"查询检查组成功",checkGroups);
    }
    @RequestMapping("/findById")
    public Result findById(int id){
       CheckGroup checkGroup=checkGroupService.findById(id);
       return new Result(true,MessageConstant.ADD_CHECKGROUP_SUCCESS,checkGroup);
    }
    @RequestMapping("/findCheckItemIdsByCheckGroupId")
    public Result findCheckItemIdsByCheckGroupId(int id){
        List<Integer> ids=checkGroupService.findCheckItemIdsByCheckGroupId(id);
        return new Result(true,MessageConstant.QUERY_CHECKITEM_SUCCESS,ids);
    }
    @RequestMapping("/update")
    public Result update(@RequestBody CheckGroup checkGroup, Integer[] checkitemIds){
        checkGroupService.update(checkGroup,checkitemIds);
        return new Result(true,MessageConstant.EDIT_CHECKGROUP_SUCCESS);
    }
    @RequestMapping("/deleteById")
    public Result delete(int id){
        checkGroupService.delete(id);
        return new Result(true,MessageConstant.DELETE_CHECKGROUP_SUCCESS);
    }
}
