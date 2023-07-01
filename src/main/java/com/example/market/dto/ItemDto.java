package com.example.market.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto {
    private String name;
    //物品的类别，如书籍、电子产品、生活用品等
    private String category;
    //物品的描述，包括物品的状态、使用情况、优缺点等
    private String description;
    //物品的图片路径，默认为系统提供的默认图片
    private String image;
    //物品的价格，单位为元
    private Double price;
    //物品的交易方式，如面交、快递等
    private String trade;
}
