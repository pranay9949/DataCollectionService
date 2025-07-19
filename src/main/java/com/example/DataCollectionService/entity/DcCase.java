package com.example.DataCollectionService.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="CASE_DETAILS")
@Data
public class DcCase {


    @Id
    private Long caseNumber;

    private Integer appId;

    private Long planId;


}
