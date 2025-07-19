package com.example.DataCollectionService.dto;


import jakarta.persistence.Column;
import lombok.Data;

@Data
public class EducationRequest {

    private  String highestDegree;


    private Integer graduationYear;


    private  String universityName;


    private Long caseNumber;
}
