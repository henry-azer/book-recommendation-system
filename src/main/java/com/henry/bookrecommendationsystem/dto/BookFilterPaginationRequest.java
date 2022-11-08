package com.henry.bookrecommendationsystem.dto;

import com.henry.bookrecommendationsystem.enums.BookCategory;
import lombok.*;

import java.util.Set;

/**
 * @author Henry Azer
 * @since 08/11/2022
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BookFilterPaginationRequest {
    private String name;
    private Set<BookCategory> categories;
    private Double fromPrice;
    private Double toPrice;
    private Integer fromPagesNumber;
    private Integer toPagesNumber;
    private Integer fromReadingDuration;
    private Integer toReadingDuration;

    public Set<BookCategory> getCategories() {
        if (categories.isEmpty()) return null;
        return categories;
    }
}
