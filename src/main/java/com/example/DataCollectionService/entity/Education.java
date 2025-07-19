package com.example.DataCollectionService.entity;


import jakarta.persistence.*;
import lombok.Data;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;

@Entity
@Table(name="EDUCATION_DETAILS")
@Data
public class Education {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Column(name="HIGHEST_DEGREE")
    private  String highestDegree;

    @Column(name="GRADUATION_YEAR")
    private Integer graduationYear;

    @Column(name="UNIVERSITY_NAME")
    private  String universityName;

    @Column(name="CASE_NUMBER")
    private Long caseNumber;


    @Column(name="CREATED_AT",updatable = false)
    @CreationTimestamp
    private LocalDate createdAt;

    @Column(name="UPDATED_AT",insertable = false)
    @UpdateTimestamp
    private LocalDate updatedAt;



}
