package com.example.DataCollectionService.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserRegisteredSsnResponse {
    private Long ssn;
    private LocalDate dateOfBirth;
    private String  userName;
}
