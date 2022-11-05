package com.henry.bookrecommendationsystem.controller.base;

import com.henry.bookrecommendationsystem.service.base.BaseService;

public interface BaseController<Service extends BaseService> {
    Service getService();
}
