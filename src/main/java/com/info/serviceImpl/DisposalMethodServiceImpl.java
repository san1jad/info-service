package com.info.serviceImpl;

import com.common.dto.info.DisposalMethodDTO;
import com.common.exception.HandleNotFoundException;
import com.common.vo.info.DisposalMethodVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.info.entity.DisposalMethod;
import com.info.repo.DisposalMethodRepository;
import com.info.service.DisposalMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DisposalMethodServiceImpl implements DisposalMethodService {

    private final DisposalMethodRepository disposalMethodRepository;

    @Autowired
    public DisposalMethodServiceImpl(DisposalMethodRepository disposalMethodRepository) {
        this.disposalMethodRepository = disposalMethodRepository;
    }

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public List<DisposalMethodVO> getAllDisposalMethods() {
        return disposalMethodRepository.findAll()
                .stream()
                .map(disposalMethod -> objectMapper.convertValue(disposalMethod,DisposalMethodVO.class))
                .collect(Collectors.toList());
    }

    @Override
    public DisposalMethodVO getDisposalMethodById(Long id) {
        return disposalMethodRepository.findById(id)
                .map(disposalMethod -> objectMapper.convertValue(disposalMethod,DisposalMethodVO.class))
                .orElseThrow(() -> new HandleNotFoundException("Disposal method for id not found"));
    }

    @Override
    public DisposalMethodVO createDisposalMethod(DisposalMethodDTO disposalMethodDto) {
        DisposalMethod disposalMethod= objectMapper.convertValue(disposalMethodDto,DisposalMethod.class);
        return objectMapper.convertValue(disposalMethodRepository.save(disposalMethod),DisposalMethodVO.class);
    }

    @Override
    public DisposalMethodVO updateDisposalMethod(Long id, DisposalMethodDTO newDisposalMethodDTO) {
        return disposalMethodRepository.findById(id)
                .map(optionalDisposalMethod -> {
                    DisposalMethod existingDisposalMethod = optionalDisposalMethod;
                    existingDisposalMethod.setMethod(newDisposalMethodDTO.getMethod());
                    return objectMapper.convertValue(disposalMethodRepository.save(existingDisposalMethod),DisposalMethodVO.class);
                })
                .orElseThrow(() -> new HandleNotFoundException("not found"));
    }

    @Override
    public void deleteDisposalMethod(Long id) {
        disposalMethodRepository.deleteById(id);
    }
}
