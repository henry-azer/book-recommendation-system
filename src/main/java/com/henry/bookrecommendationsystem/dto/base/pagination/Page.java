package com.henry.bookrecommendationsystem.dto.base.pagination;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Henry Azer
 * @since 03/11/2022
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Page {
    private int pageNumber;
    private int pageSize;
}