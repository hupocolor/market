package com.example.market.domain.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.market.domain.entity.Item;
import com.example.market.dto.ItemDto;
import com.example.market.utils.ResponseResult;

/**
 * (Item)表服务接口
 *
 * @author makejava
 * @since 2023-05-19 08:49:37
 */
public interface ItemService extends IService<Item> {

    ResponseResult addItem(ItemDto itemDto);

    ResponseResult deleteItem(Integer id);

    ResponseResult getPageList(Integer pageNum, Integer pageSize, String keyword, Integer userId);

    ResponseResult buyItem(Integer itemId);
}

