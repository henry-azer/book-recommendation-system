package com.henry.bookrecommendationsystem.dto;

import com.henry.bookrecommendationsystem.dto.base.BaseDto;
import com.henry.bookrecommendationsystem.enums.UserReadingLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author Henry Azer
 * @since 10/11/2022
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserReadingInfoDto extends BaseDto {
    private Long id;
    private UserDto user;
    private UserReadingLevel readingLevel;
    private List<UserBookCategoryDto> userBookCategories;
}
