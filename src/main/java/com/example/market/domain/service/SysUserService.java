package com.example.market.domain.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.market.domain.entity.SysUser;
import com.example.market.utils.ResponseResult;
import org.springframework.http.ResponseEntity;

/**
 * 系统用户(SysUser)表服务接口
 *
 * @author makejava
 * @since 2023-05-12 16:59:32
 */
public interface SysUserService extends IService<SysUser> {

    ResponseResult login(SysUser user);
}

