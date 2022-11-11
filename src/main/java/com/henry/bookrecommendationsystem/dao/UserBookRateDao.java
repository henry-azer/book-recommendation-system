package com.henry.bookrecommendationsystem.dao;

import com.henry.bookrecommendationsystem.dao.base.BaseDao;
import com.henry.bookrecommendationsystem.entity.UserBookRate;
import com.henry.bookrecommendationsystem.repository.UserBookRatingRepository;

import java.util.Optional;

/**
 * @author Henry Azer
 * @since 08/11/2022
 */
public interface UserBookRateDao extends BaseDao<UserBookRate, UserBookRatingRepository> {
    Optional<UserBookRate> findUserBookRateByUserIdAndBookId(Long userId, Long bookId);
}
