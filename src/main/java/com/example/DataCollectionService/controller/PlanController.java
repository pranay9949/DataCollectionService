package com.example.DataCollectionService.controller;


import com.example.DataCollectionService.dto.PlanRequest;
import com.example.DataCollectionService.exception.CaseNumberNotFoundException;
import com.example.DataCollectionService.exception.DuplicateCaseNumberException;
import com.example.DataCollectionService.exception.NoPlanFoundException;
import com.example.DataCollectionService.service.DataCollectionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/plan")
public class PlanController {
    private DataCollectionService dataCollectionService;

    public PlanController(DataCollectionService dataCollectionService) {
        this.dataCollectionService = dataCollectionService;
    }

    @GetMapping("/allplans")
    public ResponseEntity<Map<Long,String>> getAllPlans(){
        Map<Long,String> mp = dataCollectionService.getPlanNames();
        return new ResponseEntity<>(mp, HttpStatus.OK);

    }

    @PostMapping("/addplan")
    public ResponseEntity<Long> addPlan(@RequestBody PlanRequest request) throws NoPlanFoundException, CaseNumberNotFoundException, DuplicateCaseNumberException {


       Long isPlanSaved = dataCollectionService.
               addPlan(request);
       // String message = isPlanSaved ? "Plan Saved Successful" : "Plan Saved Failed";

        return new ResponseEntity<>(isPlanSaved,HttpStatus.OK);

    }
}
