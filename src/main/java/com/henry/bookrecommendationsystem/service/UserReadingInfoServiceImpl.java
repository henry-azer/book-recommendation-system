package com.henry.bookrecommendationsystem.service;

import com.henry.bookrecommendationsystem.dao.UserReadingInfoDao;
import com.henry.bookrecommendationsystem.dto.UserReadingInfoDto;
import com.henry.bookrecommendationsystem.entity.UserReadingInfo;
import com.henry.bookrecommendationsystem.transformer.UserReadingInfoTransformer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.transaction.Transactional;
import java.util.Optional;

/**
 * @author Henry Azer
 * @since 10/11/2022
 */
@Slf4j
@Service
public class UserReadingInfoServiceImpl implements UserReadingInfoService {
    private final UserReadingInfoDao userReadingInfoDao;
    private final UserReadingInfoTransformer userReadingInfoTransformer;
    private final UserService userService;
    private final UserBookCategoryService userBookCategoryService;

    public UserReadingInfoServiceImpl(UserReadingInfoDao userReadingInfoDao, UserReadingInfoTransformer userReadingInfoTransformer, UserService userService, UserBookCategoryService userBookCategoryService) {
        this.userReadingInfoDao = userReadingInfoDao;
        this.userReadingInfoTransformer = userReadingInfoTransformer;
        this.userService = userService;
        this.userBookCategoryService = userBookCategoryService;
    }

    @Override
    public UserReadingInfoDao getDao() {
        return userReadingInfoDao;
    }

    @Override
    public UserReadingInfoTransformer getTransformer() {
        return userReadingInfoTransformer;
    }

    @Override
    @Transactional
    public UserReadingInfoDto create(UserReadingInfoDto dto) {
        log.info("UserReadingInfoService: create() called");
        dto.setUser(userService.getCurrentUser());
        dto.getUserBookCategories().forEach(userBookCategoryDto -> userBookCategoryDto.setUser(userService.getCurrentUser()));
        UserReadingInfoDto userReadingInfoDto = getTransformer().transformEntityToDto(getDao().create(getTransformer().transformDtoToEntity(dto)));
        userReadingInfoDto.setUserBookCategories(userBookCategoryService.findAllUserBookCategories());
        return userReadingInfoDto;
    }

    @Override
    public UserReadingInfoDto findUserReadingInfo() {
        log.info("UserReadingInfoService: findUserReadingInfo() called");
        Optional<UserReadingInfo> userReadingInfo = getDao().findByUserId(userService.getCurrentUser().getId());
        if (userReadingInfo.isEmpty()) throw new EntityExistsException("User hasn't reading info");
        UserReadingInfoDto userReadingInfoDto = getTransformer().transformEntityToDto(userReadingInfo.get());
        userReadingInfoDto.setUserBookCategories(userBookCategoryService.findAllUserBookCategories());
        return userReadingInfoDto;
    }
}
