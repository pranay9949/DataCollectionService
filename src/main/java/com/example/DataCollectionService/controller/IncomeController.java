package com.example.DataCollectionService.controller;


import com.example.DataCollectionService.dto.IncomeRequest;
import com.example.DataCollectionService.exception.CaseNumberNotFoundException;
import com.example.DataCollectionService.exception.DuplicateCaseNumberException;
import com.example.DataCollectionService.repo.IncomeRepo;
import com.example.DataCollectionService.service.DataCollectionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/income")
public class IncomeController {
    private DataCollectionService dataCollectionService;

    public IncomeController(DataCollectionService dataCollectionService) {
        this.dataCollectionService = dataCollectionService;
    }

    @PostMapping("/addincome")
    public ResponseEntity<Long> saveIncome(@RequestBody IncomeRequest request) throws DuplicateCaseNumberException, CaseNumberNotFoundException {
        Long caseNum = dataCollectionService.saveIncomeData(request);
        return new ResponseEntity<>(caseNum, HttpStatus.OK);
    }

}
