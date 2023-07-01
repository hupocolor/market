package com.example.market.controller;

import com.example.market.domain.entity.SysUser;
import com.example.market.domain.service.SysUserService;
import com.example.market.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author : hupo, 创建于:2023/5/23
 */
@RestController
@RequestMapping("/info")
public class LoginUserController {
    @Autowired
    SysUserService service;
    @GetMapping("/getUserInfo")
    public ResponseResult getUserInfo(){
        return service.getUserInfo();
    }

    @PutMapping("/saveUserInfo")
    public ResponseResult saveUserInfo(@RequestBody SysUser user){
        return service.updateUserInfo(user);
    }

}
