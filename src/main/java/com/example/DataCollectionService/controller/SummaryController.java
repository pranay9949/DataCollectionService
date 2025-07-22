package com.example.DataCollectionService.controller;


import com.example.DataCollectionService.dto.Summary;
import com.example.DataCollectionService.exception.CaseNumberNotFoundException;
import com.example.DataCollectionService.service.DataCollectionService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/summary")
public class SummaryController {
    private DataCollectionService dataCollectionService;
    public SummaryController(DataCollectionService dataCollectionService) {
        this.dataCollectionService = dataCollectionService;
    }

    @GetMapping("/get/{caseNumber}")
    public ResponseEntity<Summary> getAllSavedData(@PathVariable Long caseNumber) throws CaseNumberNotFoundException {
        Summary summary = dataCollectionService.getSavedData(caseNumber);
        return new ResponseEntity<>(summary, HttpStatus.OK);
    }

}
