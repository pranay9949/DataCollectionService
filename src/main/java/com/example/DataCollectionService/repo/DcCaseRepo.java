package com.example.DataCollectionService.repo;

import com.example.DataCollectionService.entity.DcCase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DcCaseRepo extends JpaRepository<DcCase,Long> {
    Boolean existsByAppId(Integer id);
}
