package com.example.DataCollectionService.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Plan_Type_Details")
@Data
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Long caseNumber;

    private String planName;
}
