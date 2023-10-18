package com.info.serviceImpl;

import com.common.exception.HandleNotFoundException;
import com.common.vo.info.ItemVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.info.entity.Item;
import com.info.repo.ItemRepository;
import com.info.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public List<ItemVO> getAllItems() {
        return itemRepository.findAll()
                .stream()
                .map(item -> objectMapper.convertValue(item, ItemVO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ItemVO getItemById(Long id) {
        return itemRepository.findById(id)
                .map(item -> objectMapper.convertValue(item, ItemVO.class))
                .orElse(null);
    }

    @Override
    public ItemVO createItem(Item item) {
        return objectMapper.convertValue(itemRepository.save(item), ItemVO.class);
    }

    @Override
    public ItemVO updateItem(Long id, Item newItem) {
        return itemRepository.findById(id)
                .map(existingItem -> {
                    existingItem.setName(newItem.getName());
                    return objectMapper.convertValue(itemRepository.save(existingItem),ItemVO.class);
                }).orElseThrow(() -> new HandleNotFoundException("Item is not found"));
    }

    @Override
    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }
}
