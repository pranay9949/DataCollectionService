package com.example.DataCollectionService.feign;


import com.example.DataCollectionService.dto.UserRegisteredSsnResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="APPLICATIONREGISTRATIONSERVICE")
public interface ApplicationRegistrationClient {

    @GetMapping("/api/registration/get/appid/{appId}")
    public ResponseEntity<UserRegisteredSsnResponse> getUserBySsn(@PathVariable Integer appId);
}
