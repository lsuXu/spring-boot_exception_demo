package com.example.exception_demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionHandle {

    Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @ExceptionHandler(value = Exception.class)//注解需要捕获的异常类
    @ResponseBody
    public Result handle(Exception e){
        //判断捕获的异常是不是自定义异常的子类或对象
        if(e instanceof FoodException){
            FoodException foodException = (FoodException)e;
            return ResultUtil.error(foodException.getCode(),foodException.getMessage());
        }else {
            
            //系统异常则在控制台打印异常并且保持格式返回前端
            logger.error("出未知错误：{}",e);
            return ResultUtil.error(400,e.getMessage());
        }
    }
}
