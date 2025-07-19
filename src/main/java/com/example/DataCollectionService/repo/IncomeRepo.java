package com.example.DataCollectionService.repo;

import com.example.DataCollectionService.entity.Income;
import com.example.DataCollectionService.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncomeRepo extends JpaRepository<Income,Integer> {
    Boolean existsByCaseNumber(Long caseNumber);
    Income findByCaseNumber(Long caseNumber);

}
