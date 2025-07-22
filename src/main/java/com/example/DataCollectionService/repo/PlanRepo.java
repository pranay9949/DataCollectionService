package com.example.DataCollectionService.repo;

import com.example.DataCollectionService.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanRepo extends JpaRepository<Plan,Integer> {
    Boolean existsByPlanName(String planName);
    Plan findByCaseNumber(Long caseNumber);
    Boolean existsByCaseNumber(Long CaseNumber);
}
