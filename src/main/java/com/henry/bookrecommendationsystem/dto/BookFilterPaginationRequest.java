package com.henry.bookrecommendationsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

/**
 * @author Henry Azer
 * @since 08/11/2022
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookFilterPaginationRequest {
    private String name;
    private Set<String> categories;
    private Double fromPrice;
    private Double toPrice;
    private Integer fromPagesNumber;
    private Integer toPagesNumber;
    private Integer fromReadingDuration;
    private Integer toReadingDuration;

    public Set<String> getCategories() {
        if (categories.isEmpty()) return null;
        return categories;
    }
}
