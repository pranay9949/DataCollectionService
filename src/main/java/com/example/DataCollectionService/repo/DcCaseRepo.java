package com.example.DataCollectionService.repo;

import com.example.DataCollectionService.entity.DcCase;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface DcCaseRepo extends JpaRepository<DcCase,Long> {
    Boolean existsByAppId(Integer id);

    DcCase findByPlanId(Long planId);

    List<DcCase> findByAppId(Integer appId);
}
