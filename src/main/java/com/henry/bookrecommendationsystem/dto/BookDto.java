package com.henry.bookrecommendationsystem.dto;

import com.henry.bookrecommendationsystem.dto.base.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * @author Henry Azer
 * @since 05/11/2022
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookDto extends BaseDto {
    private Long id;
    private AuthorDto author;
    private String name;
    private Double rate;
    private Long usersRateCount;
    private BookCategoryDto category;
    private Double price;
    private Integer pagesNumber;
    private Integer readingDuration;
    private Date publishDate;
    private String description;
    private String imageUrl;
}
