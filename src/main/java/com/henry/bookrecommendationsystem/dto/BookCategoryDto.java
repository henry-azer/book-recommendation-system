package com.henry.bookrecommendationsystem.dto;

import com.henry.bookrecommendationsystem.dto.base.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Henry Azer
 * @since 10/11/2022
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookCategoryDto extends BaseDto {
    private Long id;
    private String name;
    private String description;
}
