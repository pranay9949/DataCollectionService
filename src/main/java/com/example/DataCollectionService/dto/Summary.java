package com.example.DataCollectionService.dto;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;
import lombok.Generated;

@Data
public class Summary {

   private Integer appId;
    private PlanRequest planRequest;
    private IncomeRequest incomeRequest;
    private EducationRequest educationRequest;
    private ChildrenRequestWrapper childrenRequestWrapper;


}
