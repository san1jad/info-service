package com.info.controller;


import com.common.dto.info.DisposalMethodDTO;
import com.common.exception.HandleNotFoundException;
import com.common.exception.HandledInternalServerException;
import com.common.vo.info.DisposalMethodVO;
import com.info.service.DisposalMethodService;
import io.micrometer.observation.annotation.Observed;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/disposal-methods")
@Validated
public class DisposalMethodController {

    private final DisposalMethodService disposalMethodService;

    @Autowired
    public DisposalMethodController(DisposalMethodService disposalMethodService) {
        this.disposalMethodService = disposalMethodService;
    }

    @GetMapping
    @Observed(
            name = "INFO",
            contextualName = "Disposal service --> get all",
            lowCardinalityKeyValues = {"DisposalGetAll","V1"}
    )
    public ResponseEntity<List<DisposalMethodVO>> getAllDisposalMethods() {
        return Optional.ofNullable(disposalMethodService.getAllDisposalMethods())
                .map(disposalMethods -> ResponseEntity.ok(disposalMethods))
                .orElseThrow(() -> new HandleNotFoundException("Disposal method's not found."));
    }

    @GetMapping("/{id}")
    @Observed(
            name = "INFO",
            contextualName = "Disposal service --> get by id",
            lowCardinalityKeyValues = {"DisposalGetById","V1"}
    )
    public ResponseEntity<DisposalMethodVO> getDisposalMethodById(@PathVariable ("id") Long id) {
        return Optional.ofNullable(disposalMethodService.getDisposalMethodById(id))
                .map(disposalMethod -> ResponseEntity.ok(disposalMethod))
                .orElseThrow(() -> new HandleNotFoundException("Disposal method with "+id+" not found."));
    }

    @PostMapping
    @Observed(
            name = "INFO",
            contextualName = "Disposal service --> create new",
            lowCardinalityKeyValues = {"DisposalNew","V1"}
    )
    public ResponseEntity<DisposalMethodVO> createDisposalMethod(@Valid @RequestBody DisposalMethodDTO disposalMethod) {
        return Optional.ofNullable(disposalMethodService.createDisposalMethod(disposalMethod))
                .map(disposalMethods -> ResponseEntity.ok(disposalMethods))
                .orElseThrow(() -> new HandledInternalServerException("Something went wrong while save. Please try after some time ! "));
    }

    @PutMapping("/{id}")
    @Observed(
            name = "INFO",
            contextualName = "Disposal service --> update",
            lowCardinalityKeyValues = {"DisposalUpdate","V1"}
    )
    public ResponseEntity<DisposalMethodVO> updateDisposalMethod(@PathVariable Long id, @Valid @RequestBody DisposalMethodDTO disposalMethodDTO) {
        return Optional.ofNullable(disposalMethodService.updateDisposalMethod(id, disposalMethodDTO))
                .map(disposalMethod -> ResponseEntity.ok(disposalMethod ))
                .orElseThrow(() -> new HandleNotFoundException("Disposal method with "+id+" not found."));
    }

    @DeleteMapping("/{id}")
    @Observed(
            name = "INFO",
            contextualName = "Disposal service --> delete",
            lowCardinalityKeyValues = {"DisposalDelete","V1"}
    )
    public ResponseEntity<Void> deleteDisposalMethod(@PathVariable Long id) {
        disposalMethodService.deleteDisposalMethod(id);
        return ResponseEntity.noContent().build();
    }
}
