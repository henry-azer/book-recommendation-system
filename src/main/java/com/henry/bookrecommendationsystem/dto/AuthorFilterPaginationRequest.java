package com.henry.bookrecommendationsystem.dto;

import lombok.*;

/**
 * @author Henry Azer
 * @since 08/11/2022
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AuthorFilterPaginationRequest {
    private String name;
}
