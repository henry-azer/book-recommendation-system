package com.henry.bookrecommendationsystem.service;


import com.henry.bookrecommendationsystem.dao.UserReadingInfoDao;
import com.henry.bookrecommendationsystem.dto.UserReadingInfoDto;
import com.henry.bookrecommendationsystem.entity.UserReadingInfo;
import com.henry.bookrecommendationsystem.service.base.BaseService;
import com.henry.bookrecommendationsystem.transformer.UserReadingInfoTransformer;

/**
 * @author Henry Azer
 * @since 10/11/2022
 */
public interface UserReadingInfoService extends BaseService<UserReadingInfo, UserReadingInfoDto, UserReadingInfoDao, UserReadingInfoTransformer> {
    UserReadingInfoDto findUserReadingInfo();
}
