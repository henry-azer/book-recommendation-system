package com.henry.bookrecommendationsystem.dto;

import com.henry.bookrecommendationsystem.dto.base.BaseDto;
import com.henry.bookrecommendationsystem.enums.BookCategory;
import lombok.*;

import java.util.Date;

/**
 * @author Henry Azer
 * @since 05/11/2022
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BookDto extends BaseDto {
    private Long id;
    private AuthorDto author;
    private String name;
    private BookCategory category;
    private Double price;
    private Integer pagesNumber;
    private Integer readingDuration;
    private Date publishDate;
    private String description;
    private String imageUrl;
}
