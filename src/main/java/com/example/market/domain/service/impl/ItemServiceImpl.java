package com.example.market.domain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.market.domain.entity.SysUser;
import com.example.market.domain.mapper.ItemMapper;
import com.example.market.domain.entity.Item;
import com.example.market.domain.service.ItemService;
import com.example.market.domain.service.SysUserService;
import com.example.market.dto.ItemDto;
import com.example.market.enums.AppHttpCodeEnum;
import com.example.market.utils.ClassMapper;
import com.example.market.utils.RedisCache;
import com.example.market.utils.ResponseResult;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * (Item)表服务实现类
 *
 * @author makejava
 * @since 2023-05-19 08:49:37
 */
@Service("itemService")
public class ItemServiceImpl extends ServiceImpl<ItemMapper, Item> implements ItemService {

    @Autowired
    RedisCache redisCache;
    @Autowired
    SysUserService userService;
    @Override
    public ResponseResult addItem(ItemDto itemDto) {
            AppHttpCodeEnum resCode = AppHttpCodeEnum.SUCCESS;
        try {
            //判断物品名字是否为空
            if (Strings.isBlank(itemDto.getName())) resCode = AppHttpCodeEnum.SYSTEM_ERROR;
            else if(getOne(new LambdaQueryWrapper<Item>().eq(Item::getName,itemDto.getName()))!=null) resCode = AppHttpCodeEnum.SYSTEM_ERROR;
            else {
                System.out.println("item以保存");
                Item item = ClassMapper.mapClass(itemDto, Item.class);
                System.out.println(item);
                save(item);
            }
        } catch (IllegalAccessException | InstantiationException e) {
            resCode = AppHttpCodeEnum.SYSTEM_ERROR;
            throw new RuntimeException(e);
        } finally {
            return ResponseResult.okResult(resCode);
        }
    }

    @Override
    public ResponseResult deleteItem(Integer id) {
        if (removeById(id)) return ResponseResult.okResult();
        return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
    }

    @Override
    public ResponseResult getPageList(Integer pageNum, Integer pageSize, String keyword, Integer userId) {
        LambdaQueryWrapper<Item> itemWrapper = new LambdaQueryWrapper<>();
        if (userId != null) itemWrapper.eq(Item::getSellerId,userId);
        //模糊查询,关键字识别分类，描述，名称
        if (!Strings.isBlank(keyword)) {
            itemWrapper.like(Item::getName, keyword).or();
            itemWrapper.like(Item::getCategory,keyword).or();
            itemWrapper.like(Item::getDescription,keyword);
        }
        Page<Item> itemPage = new Page<>(pageNum,pageSize);
        Page<Item> page = page(itemPage, itemWrapper);
        return ResponseResult.okResult(page);
    }

    @Override
    public ResponseResult buyItem(Integer itemId) {
        Integer userId = redisCache.getCacheObject("userId");
        Item item = getById(itemId);
        if (userService.getById(userId).getMoney()<=item.getPrice()){
            return ResponseResult.errorResult(AppHttpCodeEnum.MONEY_NUMBER_ERROR);
        }
        userService.updateById(userService.getById(userId).buy(item.getPrice()));
        return ResponseResult.okResult();
    }

}

