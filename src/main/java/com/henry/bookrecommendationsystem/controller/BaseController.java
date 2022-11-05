package com.henry.bookrecommendationsystem.controller;

import com.henry.bookrecommendationsystem.service.BaseService;

public interface BaseController<Service extends BaseService> {
    Service getService();
}
