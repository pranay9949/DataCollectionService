package com.example.DataCollectionService.service;

import com.example.DataCollectionService.dto.*;
import com.example.DataCollectionService.entity.Children;
import com.example.DataCollectionService.entity.Plan;
import com.example.DataCollectionService.exception.AppIdErrorException;
import com.example.DataCollectionService.exception.CaseNumberNotFoundException;
import com.example.DataCollectionService.exception.DuplicateCaseNumberException;
import com.example.DataCollectionService.exception.NoPlanFoundException;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.List;
import java.util.Map;

public interface DataCollectionService {
    public Long appIdValidate(Integer id) throws AppIdErrorException;

    public Map<Long,String> getPlanNames();
    public Long addPlan(PlanRequest request) throws NoPlanFoundException, CaseNumberNotFoundException, DuplicateCaseNumberException;

    public Long saveIncomeData(IncomeRequest incomeRequest) throws CaseNumberNotFoundException, DuplicateCaseNumberException;

    public Long saveEducationData(EducationRequest educationRequest) throws CaseNumberNotFoundException, DuplicateCaseNumberException;

    public Long saveChildrenData(ChildrenRequestWrapper requestWrapper) throws CaseNumberNotFoundException, DuplicateCaseNumberException;

    public Summary getSavedData(Long caseNumber) throws CaseNumberNotFoundException;

}
