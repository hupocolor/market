package com.example.market.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author : hupo, 创建于:2023/5/22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserShow {
    private Integer userid;
    private String username;
    private String avatar;

}
