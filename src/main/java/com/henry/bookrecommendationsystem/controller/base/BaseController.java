package com.henry.bookrecommendationsystem.controller.base;

import com.henry.bookrecommendationsystem.service.base.BaseService;

/**
 * @author Henry Azer
 * @since 03/11/2022
 */
public interface BaseController<Service extends BaseService> {
    Service getService();
}
