package com.henry.bookrecommendationsystem.controller;

import com.henry.bookrecommendationsystem.controller.base.BaseController;
import com.henry.bookrecommendationsystem.dto.UserDto;
import com.henry.bookrecommendationsystem.dto.base.response.ApiResponse;
import com.henry.bookrecommendationsystem.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * @author Henry Azer
 * @since 05/11/2022
 */
@Slf4j
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

    @GetMapping("/find-all-genders")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ApiResponse getUserGenders() {
        log.info("UserController: getUserGenders() called");
        return new ApiResponse(true, LocalDateTime.now().toString(),
                "User genders fetched successfully.", getService().getUserGenders());
    }

    @GetMapping("/find-all-martial-statuses")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ApiResponse getUserMartialStatuses() {
        log.info("UserController: getUserMartialStatuses() called");
        return new ApiResponse(true, LocalDateTime.now().toString(),
                "User martial status fetched successfully.", getService().getUserMartialStatuses());
    }

    @GetMapping("/find-is-email-exists/{email}")
    public ApiResponse checkIsEmailAlreadyExists(@PathVariable String email) {
        log.info("UserController: checkIsEmailAlreadyExists() called");
        return new ApiResponse(true, LocalDateTime.now().toString(),
                "Email existence checked successfully.", getService().isUserExistsByEmail(email));
    }

    @PostMapping
    public ApiResponse createUser(@RequestBody UserDto userDto) {
        log.info("UserController: createUser() called");
        return new ApiResponse(true, LocalDateTime.now().toString(),
                "User created successfully.", getService().create(userDto));
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public ApiResponse updateUser(@RequestBody UserDto userDto) {
        log.info("UserController: updateUser() called");
        return new ApiResponse(true, LocalDateTime.now().toString(),
                "User updated successfully.", getService().update(userDto, userDto.getId()));
    }
}
