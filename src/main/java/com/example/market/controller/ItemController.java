package com.example.market.controller;

import com.example.market.domain.service.ItemService;
import com.example.market.dto.ItemDto;
import com.example.market.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/item")
public class ItemController {
    @Autowired
    ItemService itemService;
    @PostMapping("/addItem")
    public ResponseResult addItem(@RequestBody ItemDto itemDto){
        return itemService.addItem(itemDto);
    }
    @DeleteMapping("/{id}")
    public ResponseResult deleteItem(@PathVariable("id") Integer id){
        return itemService.deleteItem(id);
    }

    @GetMapping("/getAllItem")
    public ResponseResult getAllItem(){
        return ResponseResult.okResult(itemService.list());
    }
    @GetMapping("/getMyItemList/{id}")
    public ResponseResult getMyItemList(Integer pageNum,Integer pageSize,String keyword,@PathVariable("id") Integer userId){
        return itemService.getPageList(pageNum,pageSize,keyword,userId);
    }

    @GetMapping("/getItemList")
    public ResponseResult getItemList(Integer pageNum,Integer pageSize,String keyword){
        return itemService.getPageList(pageNum,pageSize,keyword,null);
    }

    @PostMapping("/buyItem{itemId}")
    public ResponseResult buyItem(@PathVariable("itemId") Integer itemId){
        return itemService.buyItem(itemId);
    }

    @GetMapping("/{id}")
    public ResponseResult getItemInfo(@PathVariable("id") Integer ItemId){
        return ResponseResult.okResult(itemService.getById(ItemId));
    }

}
