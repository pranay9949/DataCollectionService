package com.example.DataCollectionService.feign;

import com.example.DataCollectionService.dto.PlanResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

@FeignClient(name="ADMIN")
public interface PlanCategoryClient {
    @GetMapping("api/plan/categories")
    public ResponseEntity<Map<Long,String>> getAllCategories();

    @GetMapping("api/plan/getByCategoryId/{id}")
    public ResponseEntity<List<PlanResponse>> getByCatId(@PathVariable Long id);
}
