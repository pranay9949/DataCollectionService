package com.example.DataCollectionService.controller;

import com.example.DataCollectionService.dto.ChildrenRequestWrapper;
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
@RequestMapping("/api/children")
public class ChildrenController {
    private DataCollectionService dataCollectionService;
    public ChildrenController(DataCollectionService dataCollectionService) {
        this.dataCollectionService = dataCollectionService;
    }

    @PostMapping("/addchildren")
    public ResponseEntity<Long> saveEducation(@RequestBody ChildrenRequestWrapper request) throws DuplicateCaseNumberException, CaseNumberNotFoundException {
        Long caseNum = dataCollectionService.saveChildrenData(request);
        return new ResponseEntity<>(caseNum, HttpStatus.OK);
    }

}
