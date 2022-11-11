package com.henry.bookrecommendationsystem.dao;

import com.henry.bookrecommendationsystem.entity.UserReadingInfo;
import com.henry.bookrecommendationsystem.repository.UserReadingInfoRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author Henry Azer
 * @since 10/11/2022
 */
@Component
public class UserReadingInfoDaoImpl implements UserReadingInfoDao {
    private final UserReadingInfoRepository userReadingInfoRepository;

    public UserReadingInfoDaoImpl(UserReadingInfoRepository userReadingInfoRepository) {
        this.userReadingInfoRepository = userReadingInfoRepository;
    }

    @Override
    public UserReadingInfoRepository getRepository() {
        return userReadingInfoRepository;
    }

    @Override
    public Optional<UserReadingInfo> findByUserId(Long userId) {
        return getRepository().findByUserId(userId);
    }
}
