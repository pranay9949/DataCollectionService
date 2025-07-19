package com.example.DataCollectionService.dto;


import lombok.Data;

import java.util.List;

@Data
public class ChildrenRequestWrapper {
    private Long caseNumber;
    private List<ChildrenRequest> childrenRequests;

}
