package com.info.service;

import com.common.vo.info.ItemVO;
import com.info.entity.Item;

import java.util.List;

public interface ItemService {
    List<ItemVO> getAllItems();
    ItemVO getItemById(Long id);
    ItemVO createItem(Item item);
    ItemVO updateItem(Long id, Item item);
    void deleteItem(Long id);
}
