package com.henry.bookrecommendationsystem.dto;

import com.henry.bookrecommendationsystem.dto.base.BaseDto;
import com.henry.bookrecommendationsystem.enums.UserGender;
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
public class AuthorDto extends BaseDto {
    private Long id;
    private String name;
    private String description;
    private Date birthdate;
    private Date deathdate;
    private String country;
    private Integer age;
    private UserGender gender;
    private String imageUrl;
}
