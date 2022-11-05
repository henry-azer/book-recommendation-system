package com.henry.bookrecommendationsystem.dto.base.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse {
    private Boolean success;
    private String timestamp;
    private String message;
    private Object body;
}
