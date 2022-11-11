package com.henry.bookrecommendationsystem.service;

import com.henry.bookrecommendationsystem.dao.UserBookRateDao;
import com.henry.bookrecommendationsystem.dto.UserBookRateDto;
import com.henry.bookrecommendationsystem.entity.UserBookRate;
import com.henry.bookrecommendationsystem.service.base.BaseService;
import com.henry.bookrecommendationsystem.transformer.UserBookRateTransformer;

/**
 * @author Henry Azer
 * @since 09/11/2022
 */
public interface UserBookRateService extends BaseService<UserBookRate, UserBookRateDto, UserBookRateDao, UserBookRateTransformer> {
    UserBookRateDto rateBook(UserBookRateDto userBookRateDto);
}
