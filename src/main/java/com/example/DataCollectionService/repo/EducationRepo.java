package com.example.DataCollectionService.repo;

import com.example.DataCollectionService.dto.EducationRequest;
import com.example.DataCollectionService.entity.Education;
import com.example.DataCollectionService.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EducationRepo extends JpaRepository<Education,Integer> {
    Boolean existsByCaseNumber(Long caseNumber);
    Education findByCaseNumber(Long caseNumber);

}
