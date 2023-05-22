package com.example.market.controller;

import com.example.market.domain.entity.SysUser;
import com.example.market.domain.service.SysUserService;
import com.example.market.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @Author : hupo, 创建于:2023/5/12
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    SysUserService userService;
    @PostMapping("/login")
    public ResponseResult login(@RequestBody SysUser user){
        System.out.println("方法被调用");
        return userService.login(user);
    }
    @PostMapping("/register")
    public ResponseResult register(@RequestBody SysUser user){
        return userService.register(user);
    }

    @GetMapping("{id}")
    public ResponseResult getUserInfo(@PathVariable("id") Integer userId){
        return ResponseResult.okResult(userService.getById(userId));
    }

    @GetMapping("/getUserShow")
    public ResponseResult getUserShow(Integer id){
        return userService.getUserShow(id);
    }
}
