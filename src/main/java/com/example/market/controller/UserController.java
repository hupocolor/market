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
@RequestMapping("")
public class UserController {
    @Autowired
    SysUserService userService;
    @PostMapping("/login")
    public ResponseResult login(@RequestBody SysUser user){
        return userService.login(user);
    }
}
