package com.henry.bookrecommendationsystem.dao;

import com.henry.bookrecommendationsystem.entity.UserBookCategory;
import com.henry.bookrecommendationsystem.repository.UserBookCategoryRepository;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Henry Azer
 * @since 10/11/2022
 */
@Component
public class UserBookCategoryDaoImpl implements UserBookCategoryDao {
    private final UserBookCategoryRepository userBookCategoryRepository;

    public UserBookCategoryDaoImpl(UserBookCategoryRepository userBookCategoryRepository) {
        this.userBookCategoryRepository = userBookCategoryRepository;
    }

    @Override
    public UserBookCategoryRepository getRepository() {
        return userBookCategoryRepository;
    }

    @Override
    public List<UserBookCategory> findAllByUserId(Long userId) {
        return getRepository().findAllByUserIdAndMarkedAsDeletedFalse(userId);
    }
}
