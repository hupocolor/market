package com.example.market.domain.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * 系统用户(SysUser)表实体类
 *
 * @author makejava
 * @since 2023-05-12 16:59:31
 */
@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_user")
public class SysUser {
    @TableId
    private Integer userid;
    //用户名
    private String username;
    //密码
    private String userpassword;
    //用户邮箱
    private String email;
    //0男1女
    private Integer sex;

}

