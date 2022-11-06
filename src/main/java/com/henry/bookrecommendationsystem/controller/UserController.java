package com.henry.bookrecommendationsystem.controller;

import com.henry.bookrecommendationsystem.controller.base.BaseController;
import com.henry.bookrecommendationsystem.dto.UserDto;
import com.henry.bookrecommendationsystem.dto.base.response.ApiResponse;
import com.henry.bookrecommendationsystem.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * @author Henry Azer
 * @since 05/11/2022
 */
@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/api/user")
public class UserController implements BaseController<UserService> {
    private final UserService userService;

    @Override
    public UserService getService() {
        return userService;
    }

    @GetMapping("/is-email-exists/{email}")
    public ApiResponse checkIsEmailAlreadyExists(@PathVariable String email) {
        return new ApiResponse(true, LocalDateTime.now().toString(),
                "Request done successfully.", userService.isUserExistsByEmail(email));
    }

    @PostMapping("/register")
    public ApiResponse registerUser(@RequestBody UserDto userDto) {
        return new ApiResponse(true, LocalDateTime.now().toString(),
                "Request done successfully.", userService.create(userDto));
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public ApiResponse updateUser(@RequestBody UserDto userDto) {
        return new ApiResponse(true, LocalDateTime.now().toString(),
                "Request done successfully.", userService.update(userDto, userDto.getId()));
    }
}
