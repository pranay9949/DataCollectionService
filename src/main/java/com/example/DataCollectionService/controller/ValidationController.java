package com.example.DataCollectionService.controller;


import com.example.DataCollectionService.exception.AppIdErrorException;
import com.example.DataCollectionService.service.DataCollectionService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/data-collection")
public class ValidationController {

    private DataCollectionService dataCollectionService;

    public ValidationController(DataCollectionService dataCollectionService) {
        this.dataCollectionService = dataCollectionService;
    }

    @GetMapping("/get/{appId}")
    public ResponseEntity<Long> getRegisteredSsn(@PathVariable Integer appId) throws AppIdErrorException {


        Long caseNum = dataCollectionService.appIdValidate(appId);

        return new ResponseEntity<>(caseNum, HttpStatus.OK);
    }

}
