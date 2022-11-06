package com.henry.bookrecommendationsystem.dto.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author Henry Azer
 * @since 03/11/2022
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseDto {
    private LocalDateTime creationDate;
    private LocalDateTime lastModificationDate;
    private Boolean markedAsDeleted = false;
}