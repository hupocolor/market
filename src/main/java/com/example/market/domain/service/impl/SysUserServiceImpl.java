package com.example.market.domain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.market.domain.mapper.SysUserMapper;
import com.example.market.domain.entity.SysUser;
import com.example.market.domain.service.SysUserService;
import com.example.market.enums.AppHttpCodeEnum;
import com.example.market.utils.MD5Util;
import com.example.market.utils.ResponseResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * 系统用户(SysUser)表服务实现类
 *
 * @author makejava
 * @since 2023-05-12 16:59:32
 */
@Service("sysUserService")
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Override
    public ResponseResult login(SysUser user) {
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUser::getUsername,user.getUsername());
        wrapper.eq(SysUser::getUserpassword, MD5Util.md5(user.getUserpassword()));
        //判断是否存在用户,若不存在返回错误代码
        return getOne(wrapper) == null ? ResponseResult.errorResult(AppHttpCodeEnum.LOGIN_ERROR):ResponseResult.okResult();
    }
}

