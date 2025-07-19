package com.example.DataCollectionService.controller;


import com.example.DataCollectionService.dto.EducationRequest;
import com.example.DataCollectionService.exception.CaseNumberNotFoundException;
import com.example.DataCollectionService.exception.DuplicateCaseNumberException;
import com.example.DataCollectionService.service.DataCollectionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/education")
public class EducationController {

    private DataCollectionService dataCollectionService;
    public EducationController(DataCollectionService dataCollectionService) {
        this.dataCollectionService = dataCollectionService;
    }

    @PostMapping("/addeducation")
   public ResponseEntity<Long> saveEducation(@RequestBody EducationRequest request) throws DuplicateCaseNumberException, CaseNumberNotFoundException {
        Long caseNum = dataCollectionService.saveEducationData(request);
        return new ResponseEntity<>(caseNum, HttpStatus.OK);
    }

}
