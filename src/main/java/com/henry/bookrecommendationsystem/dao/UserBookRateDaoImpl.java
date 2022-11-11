package com.henry.bookrecommendationsystem.dao;

import com.henry.bookrecommendationsystem.entity.UserBookRate;
import com.henry.bookrecommendationsystem.repository.UserBookRatingRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author Henry Azer
 * @since 08/11/2022
 */
@Component
public class UserBookRateDaoImpl implements UserBookRateDao {
    private final UserBookRatingRepository userBookRatingRepository;

    public UserBookRateDaoImpl(UserBookRatingRepository userBookRatingRepository) {
        this.userBookRatingRepository = userBookRatingRepository;
    }

    @Override
    public UserBookRatingRepository getRepository() {
        return userBookRatingRepository;
    }

    @Override
    public Optional<UserBookRate> findUserBookRateByUserIdAndBookId(Long userId, Long bookId) {
        return getRepository().findUserBookRateByUserIdAndBookId(userId, bookId);
    }
}
