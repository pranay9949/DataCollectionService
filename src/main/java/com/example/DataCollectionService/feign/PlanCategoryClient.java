package com.example.DataCollectionService.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@FeignClient(name="ADMIN")
public interface PlanCategoryClient {
    @GetMapping("api/plan/categories")
    public ResponseEntity<Map<Long,String>> getAllCategories();
}
