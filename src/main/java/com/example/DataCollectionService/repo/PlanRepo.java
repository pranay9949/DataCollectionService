package com.example.DataCollectionService.repo;

import com.example.DataCollectionService.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanRepo extends JpaRepository<Plan,Integer> {
    Boolean existsByCaseNumber(Long caseNumber);
    Plan findByCaseNumber(Long caseNumber);
}
