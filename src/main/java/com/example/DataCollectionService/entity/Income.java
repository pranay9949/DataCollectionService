package com.example.DataCollectionService.entity;


import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;

@Entity
@Table(name = "INCOME_DETAILS")
@Data
public class Income {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="MONTHLY_INCOME")
    private Double monthlyIncome;

    @Column(name="RENTAL_INCOME")
    private Double rentalIncome;

    @Column(name="PROPERTY_INCOME")
    private Double propertyIncome;

    @Column(name="CASE_NUMBER")
    private Long caseNumber;


    @Column(name="CREATED_AT",updatable = false)
    @CreationTimestamp
    private LocalDate createdAt;

    @Column(name="UPDATED_AT",insertable = false)
    @UpdateTimestamp
    private LocalDate updatedAt;

}
