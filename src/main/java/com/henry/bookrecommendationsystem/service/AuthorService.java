package com.henry.bookrecommendationsystem.service;

import com.henry.bookrecommendationsystem.dao.AuthorDao;
import com.henry.bookrecommendationsystem.dto.AuthorDto;
import com.henry.bookrecommendationsystem.dto.AuthorFilterPaginationRequest;
import com.henry.bookrecommendationsystem.dto.base.pagination.FilterPaginationRequest;
import com.henry.bookrecommendationsystem.dto.base.response.PaginationResponse;
import com.henry.bookrecommendationsystem.entity.Author;
import com.henry.bookrecommendationsystem.service.base.BaseService;
import com.henry.bookrecommendationsystem.transformer.AuthorTransformer;

/**
 * @author Henry Azer
 * @since 07/11/2022
 */
public interface AuthorService extends BaseService<Author, AuthorDto, AuthorDao, AuthorTransformer> {
    PaginationResponse<AuthorDto> findAllAuthorsPaginatedAndFiltered(FilterPaginationRequest<AuthorFilterPaginationRequest> authorFilterPaginationRequest);
}
