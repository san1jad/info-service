package com.info.service;


import com.common.dto.info.DisposalMethodDTO;
import com.common.vo.info.DisposalMethodVO;

import java.util.List;

public interface DisposalMethodService {
    List<DisposalMethodVO> getAllDisposalMethods();
    DisposalMethodVO getDisposalMethodById(Long id);
    DisposalMethodVO createDisposalMethod(DisposalMethodDTO disposalMethod);
    DisposalMethodVO updateDisposalMethod(Long id, DisposalMethodDTO disposalMethod);
    void deleteDisposalMethod(Long id);
}
