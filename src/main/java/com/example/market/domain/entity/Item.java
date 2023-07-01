package com.example.market.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (Item)表实体类
 *
 * @author makejava
 * @since 2023-05-19 08:49:36
 */
@TableName("Item")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("serial")
public class Item extends Model<Item> {
    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", price=" + price +
                ", trade='" + trade + '\'' +
                ", sellerId=" + sellerId +
                ", buyerId=" + buyerId +
                ", status='" + status + '\'' +
                '}';
    }

    @TableId
    //物品的唯一标识
    private Integer id;
    //物品的名称
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
    //物品的卖家
    private Integer sellerId;
    //物品的买家，默认为null
    private Integer buyerId;
    //物品的状态，0表示未出售，1表示已出售，默认为0
    private String status;

    private String seller;

    private Integer level;
}

