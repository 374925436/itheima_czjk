package com.itheima.czjk.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.itheima.czjk.constant.MessageConstant;
import com.itheima.czjk.entity.PageResult;
import com.itheima.czjk.entity.QueryPageBean;
import com.itheima.czjk.entity.Result;
import com.itheima.czjk.pojo.Setmeal;
import com.itheima.czjk.service.SetMealService;
import com.itheima.czjk.utils.QiNiuUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestControllerAdvice
@RequestMapping("/setmeal")
public class SetMealController {
    @Reference
    private SetMealService setMealService;
    @RequestMapping("/findPage")
    public Result findPage(@RequestBody QueryPageBean queryPageBean){
       PageResult<Setmeal> pageResult=setMealService.fingPage(queryPageBean);
       return new Result(true, MessageConstant.GET_SETMEAL_LIST_SUCCESS,pageResult);
    }
    @RequestMapping("/findById")
    public Result findById(int id){
        Setmeal setmeal = setMealService.findById(id);
        Map<String, Object> datamap = new HashMap<>();
        datamap.put("domain",QiNiuUtils.DOMAIN);
        datamap.put("setmeal",setmeal);
        return new Result(true, MessageConstant.GET_SETMEAL_LIST_SUCCESS,datamap);
    }
    @RequestMapping("/findCheckGroupById")
    public Result findCheckGroupById(int id){
        Integer[] ids= setMealService.findCheckGroupById(id);
        return new Result(true, MessageConstant.GET_SETMEAL_LIST_SUCCESS,ids);
    }
    @RequestMapping("/deleteById")
    public Result deleteById(int id){
        setMealService.deleteById(id);
        return new Result(true, MessageConstant.DELETE_SETMEAL_LIST_SUCCESS);
    }
    @RequestMapping("/upload")
        public Result upload(MultipartFile imgFile){
        String originalFilename = imgFile.getOriginalFilename();
        String substring = originalFilename.substring(originalFilename.lastIndexOf("."));
        String imgName = UUID.randomUUID().toString() + substring;
        try {
            QiNiuUtils.uploadViaByte(imgFile.getBytes(),imgName);
        } catch (IOException e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.PIC_UPLOAD_FAIL);
        }
        Map<String, String> datamap=new HashMap<>();
        datamap.put("imgName",imgName);
        datamap.put("domain",QiNiuUtils.DOMAIN);
        return new Result(true, MessageConstant.PIC_UPLOAD_SUCCESS,datamap);
    }
    @RequestMapping("/addSetMeal")
    public Result addSetMeal(@RequestBody Setmeal setmeal,Integer[] id){
        setMealService.addSetMeal(setmeal,id);
        return new Result(true, MessageConstant.ADD_SETMEAL_SUCCESS);
    }
    @RequestMapping("/update")
    public Result update(@RequestBody Setmeal setmeal,Integer[] id){
        setMealService.update(setmeal,id);
        return new Result(true, "编辑套餐成功");
    }

}
