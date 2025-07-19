package com.example.DataCollectionService.service;


import com.example.DataCollectionService.dto.*;
import com.example.DataCollectionService.entity.*;
import com.example.DataCollectionService.exception.AppIdErrorException;
import com.example.DataCollectionService.exception.CaseNumberNotFoundException;
import com.example.DataCollectionService.exception.DuplicateCaseNumberException;
import com.example.DataCollectionService.exception.NoPlanFoundException;
import com.example.DataCollectionService.feign.ApplicationRegistrationClient;
import com.example.DataCollectionService.feign.PlanCategoryClient;
import com.example.DataCollectionService.repo.*;
import feign.FeignException;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class DataCollectionServiceImpl implements DataCollectionService{
    private ApplicationRegistrationClient registrationClient;
   private PlanCategoryClient categoryClient;
   private PlanRepo planRepo;
   private DcCaseRepo dcCaseRepo;
   private IncomeRepo incomeRepo;
   private EducationRepo educationRepo;
   private ChildrenRepo childrenRepo;

    public DataCollectionServiceImpl(ApplicationRegistrationClient registrationClient, PlanCategoryClient categoryClient,PlanRepo planRepo,DcCaseRepo dcCaseRepo, IncomeRepo incomeRepo, EducationRepo educationRepo, ChildrenRepo childrenRepo ) {
        this.registrationClient = registrationClient;
        this.categoryClient=categoryClient;
        this.planRepo=planRepo;
        this.dcCaseRepo=dcCaseRepo;
        this.incomeRepo=incomeRepo;
        this.educationRepo=educationRepo;
        this.childrenRepo=childrenRepo;
    }

    @Override
    public Long appIdValidate(Integer id) throws AppIdErrorException {
        try {
            UserRegisteredSsnResponse registeredSsnResponse = registrationClient.getUserBySsn(id).getBody();
            if (registeredSsnResponse == null) {
                throw new AppIdErrorException("No response from Registration Service.");
            }
            if(dcCaseRepo.existsByAppId(id)){
                throw new AppIdErrorException("Entered AppId has Already generated with Case Number");
            }
           DcCase dcCase = new DcCase();
            dcCase.setAppId(id);
            dcCase.setCaseNumber(generateUnique6DigitId());
            DcCase dc = dcCaseRepo.save(dcCase);
            System.out.println(dc.getCaseNumber());

            return  dc.getCaseNumber();
        } catch (FeignException.NotFound ex) {
            // Handle 404 from Feign call
            throw new AppIdErrorException("Provided Application Id is not registered, please register.");
        } catch (FeignException ex) {
            // Handle other Feign errors (500, 403 etc.)
            throw new AppIdErrorException("Error while calling Registration Service: " + ex.getMessage());
        }


    }

    @Override
    public Map<Long, String> getPlanNames() {
        Map<Long,String> list = categoryClient.getAllCategories().getBody();
        return list;
    }


    @Override
    @Transactional
    public Long addPlan(PlanRequest request) throws NoPlanFoundException, CaseNumberNotFoundException, DuplicateCaseNumberException {
        Plan plan = new Plan();
        Map<Long,String> plans = getPlanNames();
        if(planRepo.existsByCaseNumber(request.getCaseNumber())){
            throw new DuplicateCaseNumberException("Entered  Case Number is already registered with plan Data");
        }
        Long planId = plans.entrySet().stream()
                .filter(p -> p.getValue().equalsIgnoreCase(request.getPlanName()))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElseThrow(() -> new NoPlanFoundException("Plan not found"));
       plan.setPlanName(request.getPlanName());
       DcCase dcCase = dcCaseRepo.findById(request.getCaseNumber()).orElseThrow(()->new CaseNumberNotFoundException("Provided Case Number does Not Exist"));

       dcCase.setPlanId(planId);
       dcCaseRepo.save(dcCase);
        plan.setCaseNumber(request.getCaseNumber());
        plan.setPlanName(request.getPlanName());

       planRepo.save(plan);
       return request.getCaseNumber();
    }

    @Override
    public Long saveIncomeData(IncomeRequest incomeRequest) throws CaseNumberNotFoundException, DuplicateCaseNumberException {
        if(!planRepo.existsByCaseNumber(incomeRequest.getCaseNumber())){
            throw new CaseNumberNotFoundException("Provided Case Number does Not Exist");
        }
        if(incomeRepo.existsByCaseNumber(incomeRequest.getCaseNumber())){
            throw new DuplicateCaseNumberException("Entered  Case Number is already registered with Income Data");
        }
        Income income = new Income();
        BeanUtils.copyProperties(incomeRequest,income);
        incomeRepo.save(income);
        return income.getCaseNumber();
    }

    @Override
    public Long saveEducationData(EducationRequest educationRequest) throws CaseNumberNotFoundException, DuplicateCaseNumberException {
        if(!incomeRepo.existsByCaseNumber(educationRequest.getCaseNumber())){
            throw new CaseNumberNotFoundException("Provided Case Number does Not Exist");
        }
        if(educationRepo.existsByCaseNumber(educationRequest.getCaseNumber())){
            throw new DuplicateCaseNumberException("Entered  Case Number is already registered with Income Data");
        }
        Education education = new Education();
        BeanUtils.copyProperties(educationRequest,education);
        educationRepo.save(education);
        return education.getCaseNumber();

    }

    @Override
    public Long saveChildrenData(ChildrenRequestWrapper requestWrapper) throws CaseNumberNotFoundException, DuplicateCaseNumberException {
        if(!educationRepo.existsByCaseNumber(requestWrapper.getCaseNumber())){
            throw new CaseNumberNotFoundException("Provided Case Number does Not Exist");
        }
        if(childrenRepo.existsByCaseNumber(requestWrapper.getCaseNumber())){
            throw new DuplicateCaseNumberException("Entered  Case Number is already registered with Children Data");
        }

        List<Children> children = new ArrayList<>();

        requestWrapper.getChildrenRequests().stream().forEach(child->{
            Children children1 = new Children();
            children1.setCaseNumber(requestWrapper.getCaseNumber());
            BeanUtils.copyProperties(child,children1);
            children.add(children1);
        });
        childrenRepo.saveAll(children);

        return  requestWrapper.getCaseNumber();
    }

    @Override
    public Summary getSavedData(Long caseNumber) {
        Summary summary = new Summary();
        Plan plan = planRepo.findByCaseNumber(caseNumber);
        PlanRequest planrequest = new PlanRequest();
        BeanUtils.copyProperties(plan,planrequest);
        summary.setPlanRequest(planrequest);

        Income income = incomeRepo.findByCaseNumber(caseNumber);
        IncomeRequest incomeRequest = new IncomeRequest();
        BeanUtils.copyProperties(income,incomeRequest);
        summary.setIncomeRequest(incomeRequest);

        Education education =educationRepo.findByCaseNumber(caseNumber);
        EducationRequest educationRequest = new EducationRequest();
        BeanUtils.copyProperties(education,educationRequest);
        summary.setEducationRequest(educationRequest);

        List<Children> children   =childrenRepo.findByCaseNumber(caseNumber);
       ChildrenRequestWrapper wrapper  = new ChildrenRequestWrapper();
       List<ChildrenRequest> childrenRequests = children.stream().map(
               child->{
                   ChildrenRequest req = new ChildrenRequest();
                   BeanUtils.copyProperties(child,req);
                   return req;
               }
       ).collect(Collectors.toList());
       wrapper.setChildrenRequests(childrenRequests);
       wrapper.setCaseNumber(caseNumber);

        summary.setChildrenRequestWrapper(wrapper);

        return summary;



    }


    public Long generateUnique6DigitId() {
        long id;
        do {
            id = 100000 + new Random().nextInt(900000);
        } while (dcCaseRepo.existsById(id));
        return id;
    }

}
