package com.example.market.domain.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.market.domain.mapper.SysUserMapper;
import com.example.market.domain.entity.SysUser;
import com.example.market.domain.service.SysUserService;
import com.example.market.dto.UserShow;
import com.example.market.enums.AppHttpCodeEnum;
import com.example.market.utils.*;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * 系统用户(SysUser)表服务实现类
 *
 * @author makejava
 * @since 2023-05-12 16:59:32
 */
@Service("sysUserService")
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    RedisCache redisCache;
    @Override
    public ResponseResult login(SysUser user) {
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUser::getUsername,user.getUsername());
        wrapper.eq(SysUser::getUserpassword, MD5Util.md5(user.getUserpassword()));
        //判断是否存在用户,若不存在返回错误代码
        if(Strings.isBlank(user.getUserpassword()) ||Strings.isBlank(user.getUsername())) {
            return ResponseResult.errorResult(AppHttpCodeEnum.USER_INFO_ERROR);
        }
        //若登录成功则对增加redis缓存,将登录的用户状态信息存储到redis中
        if (getOne(wrapper)!=null) redisCache.setCacheObject("userInfo",getOne(wrapper));
        return getOne(wrapper) == null ? ResponseResult.errorResult(AppHttpCodeEnum.LOGIN_ERROR):ResponseResult.okResult(getOne(wrapper));
    }

    @Override
    public ResponseResult register(SysUser user) {
        //账号密码不能为空
        if(Strings.isBlank(user.getUserpassword()) ||Strings.isBlank(user.getUsername())) {
            return ResponseResult.errorResult(AppHttpCodeEnum.USER_INFO_ERROR);
        }
        //判断邮箱格式是否正确
        if(!ValidationUtils.isEmailValid(user.getEmail())){
            return ResponseResult.errorResult(AppHttpCodeEnum.EMAIL_EXIST);
        }
        if(getOne(new LambdaQueryWrapper<SysUser>().eq(SysUser::getEmail,user.getEmail()))!=null){
            return ResponseResult.errorResult(AppHttpCodeEnum.EMAIL_EXIST);
        }
        //判断密码是否格式正确，只能6-18位
        if (!ValidationUtils.isPasswordValid(user.getUserpassword())){
            return ResponseResult.errorResult(AppHttpCodeEnum.LOGIN_ERROR);
        }
        //是否存在重复用户
        if(getOne(new LambdaQueryWrapper<SysUser>().eq(SysUser::getUsername,user.getUsername()))!=null){
            return ResponseResult.errorResult(AppHttpCodeEnum.USERNAME_EXIST);
        }
        //判断正确返回成功,将密码按照MD5加密后存储
        user.setUserpassword(MD5Util.md5(user.getUserpassword()));
        save(user);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult getUserShow(Integer id) {
        SysUser user = getById(id);
        UserShow userShow;
        try {
            userShow = ClassMapper.mapClass(user, UserShow.class);
        } catch (IllegalAccessException | InstantiationException e) {
            return ResponseResult.errorResult();
        }
        return ResponseResult.okResult(userShow);
    }

    @Override
    public ResponseResult getUserInfo() {
        SysUser user = JSON.parseObject(redisCache.getCacheObject("userInfo").toString(), SysUser.class);
        if (user == null) return ResponseResult.errorResult();
        return ResponseResult.okResult(user);
    }

    @Override
    public ResponseResult logout() {
        redisCache.deleteObject("userInfo");
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult updateUserInfo(SysUser user) {
        SysUser redisUser = JSON.parseObject(redisCache.getCacheObject("userInfo").toString(), SysUser.class);
        Integer id = redisUser.getUserid();
        if (!Strings.isBlank(user.getUsername())) redisUser.setUsername(user.getUsername());
        if (!Strings.isBlank(user.getEmail())) redisUser.setEmail(user.getEmail());
        if (!Objects.isNull(user.getSex())) redisUser.setSex(user.getSex());
        updateById(redisUser);
        redisCache.setCacheObject("userInfo",redisUser);
        return ResponseResult.okResult();
    }
}

