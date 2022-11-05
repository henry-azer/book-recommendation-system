package com.henry.bookrecommendationsystem.dto.base.pagination;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Page {
    private int pageNumber;
    private int pageSize;
}