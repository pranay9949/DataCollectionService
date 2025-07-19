package com.example.DataCollectionService.repo;

import com.example.DataCollectionService.entity.Children;
import com.example.DataCollectionService.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChildrenRepo extends JpaRepository<Children,Integer> {
    Boolean existsByCaseNumber(Long caseNumber);

    List<Children> findByCaseNumber(Long caseNumber);

}
