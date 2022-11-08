package com.henry.bookrecommendationsystem.dto.base.response;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Henry Azer
 * @since 03/11/2022
 */
@Data
@AllArgsConstructor
public class ApiResponse {
    private Boolean success;
    private String timestamp;
    private String message;
    private Object body;
}
