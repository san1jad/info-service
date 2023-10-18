package com.info.controller;

import com.common.exception.HandleNotFoundException;
import com.common.vo.info.ItemVO;
import com.info.entity.Item;
import com.info.service.ItemService;
import io.micrometer.observation.annotation.Observed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    @Observed(
            name = "INFO",
            contextualName = "Item service --> get all",
            lowCardinalityKeyValues = {"ItemGetAll","V1"}
    )
    public ResponseEntity<List<ItemVO>> getAllItems() {
        return Optional.ofNullable(itemService.getAllItems())
                .map(items -> ResponseEntity.ok(items))
                .orElseThrow(() -> new HandleNotFoundException("Requested items not found"));
    }

    @GetMapping("/{id}")
    @Observed(
            name = "INFO",
            contextualName = "Item service --> get by id",
            lowCardinalityKeyValues = {"ItemGetById","V1"}
    )
    public ResponseEntity<ItemVO> getItemById(@PathVariable Long id) {
        return Optional.ofNullable(itemService.getItemById(id))
                .map(item -> ResponseEntity.ok(item))
                .orElseThrow(() -> new HandleNotFoundException("Requested item with id " +id+" not found"));
    }

    @PostMapping
    @Observed(
            name = "INFO",
            contextualName = "Item service --> create new",
            lowCardinalityKeyValues = {"ItemNew","V1"}
    )
    public ResponseEntity<ItemVO> createItem(@RequestBody Item item) {
        return ResponseEntity.ok(itemService.createItem(item));
    }

    @PutMapping("/{id}")
    @Observed(
            name = "INFO",
            contextualName = "Item service --> update",
            lowCardinalityKeyValues = {"ItemUpdate","V1"}
    )
    public ResponseEntity<ItemVO> updateItem(@PathVariable Long id, @RequestBody Item item) {

        return Optional.ofNullable(itemService.updateItem(id, item))
                .map(itemUpdated -> ResponseEntity.ok(itemUpdated))
                .orElseThrow(() -> new HandleNotFoundException("Requested item with id" +id+" not found"));
    }

    @DeleteMapping("/{id}")
    @Observed(
            name = "INFO",
            contextualName = "Item service --> delete",
            lowCardinalityKeyValues = {"ItemDelete","V1"}
    )
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }
}
