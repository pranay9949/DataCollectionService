package com.example.DataCollectionService.entity;


import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;

@Data
@Table(name="CHILDREN_DETAILS")
@Entity
public class Children {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer childId;

    @Column(name="CHILD_NAME")
    private String childName;

    @Column(name="CHILD_AGE")
    private Integer childAge;


    @Column(name="CHILD_SSN")
    private Long childSsn;

    @Column(name="CASE_NUMBER")
    private Long caseNumber;

    @Column(name="CREATED_AT",updatable = false)
    @CreationTimestamp
    private LocalDate createdAt;

    @Column(name="UPDATED_AT",insertable = false)
    @UpdateTimestamp
    private LocalDate updatedAt;
}
