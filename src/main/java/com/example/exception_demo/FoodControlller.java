package com.example.exception_demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class FoodControlller {

    @Autowired
    private FoodRepository foodRepository;

    @PostMapping(value = "/addFood")
    public Result addFood(@Valid Food food, BindingResult bindingResult)throws Exception{
        if(bindingResult.hasErrors()) {
            //在进行表单验证不通过的情况下，抛出异常
            throw new FoodException(100,bindingResult.getFieldError().getDefaultMessage());
        }
        //通过验证时进行数据库插入操作，并返回正确的结果集
        return ResultUtil.success(foodRepository.save(food));
    }
    //通过ID查找实物价值大于20的物品
    @GetMapping(value = "/getFood/{id}")
    public Result getFood(@PathVariable("id") Integer id)throws Exception{
        Food food = foodRepository.findOne(id);
        Integer price = food.getPrice();
        if(price<5){
            throw new  FoodException(1,"价值小于5");
        }
        if(price<20){
            throw new  FoodException(2,"价值小于20");
        }else
            return ResultUtil.success(food);
    }
}
