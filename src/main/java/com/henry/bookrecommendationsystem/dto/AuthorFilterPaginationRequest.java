package com.henry.bookrecommendationsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Henry Azer
 * @since 08/11/2022
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthorFilterPaginationRequest {
    private String name;
}
