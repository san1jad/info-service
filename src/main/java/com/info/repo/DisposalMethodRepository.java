package com.info.repo;

import com.info.entity.DisposalMethod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DisposalMethodRepository extends JpaRepository<DisposalMethod, Long> {
    // You can add custom query methods here if needed
}