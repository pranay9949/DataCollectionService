package com.example.DataCollectionService.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class IncomeRequest {



    private Double monthlyIncome;


    private Double rentalIncome;


    private Double propertyIncome;


    private Long caseNumber;

}
