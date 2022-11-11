package com.henry.bookrecommendationsystem.dao;

import com.henry.bookrecommendationsystem.dao.base.BaseDao;
import com.henry.bookrecommendationsystem.entity.UserReadingInfo;
import com.henry.bookrecommendationsystem.repository.UserReadingInfoRepository;

import java.util.Optional;

/**
 * @author Henry Azer
 * @since 10/11/2022
 */
public interface UserReadingInfoDao extends BaseDao<UserReadingInfo, UserReadingInfoRepository> {
    Optional<UserReadingInfo> findByUserId(Long userId);
}
