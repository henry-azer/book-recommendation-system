package com.henry.bookrecommendationsystem.dto.base.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Henry Azer
 * @since 03/11/2022
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthRequest {

    @NotBlank(message = "Login email cannot be blank")
    @NotNull(message = "Login email cannot be null")
    private String email;

    @NotBlank(message = "Login password cannot be blank")
    @NotNull(message = "Login password cannot be null")
    private String password;

}
