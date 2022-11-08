package com.henry.bookrecommendationsystem.service;

import com.henry.bookrecommendationsystem.dao.AuthorDao;
import com.henry.bookrecommendationsystem.dto.AuthorDto;
import com.henry.bookrecommendationsystem.dto.AuthorFilterPaginationRequest;
import com.henry.bookrecommendationsystem.dto.base.pagination.FilterPaginationRequest;
import com.henry.bookrecommendationsystem.dto.base.response.PaginationResponse;
import com.henry.bookrecommendationsystem.transformer.AuthorTransformer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Henry Azer
 * @since 07/11/2022
 */
@Slf4j
@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorTransformer authorTransformer;
    private final AuthorDao authorDao;

    public AuthorServiceImpl(AuthorTransformer authorTransformer, AuthorDao authorDao) {
        this.authorTransformer = authorTransformer;
        this.authorDao = authorDao;
    }

    @Override
    public AuthorDao getDao() {
        return authorDao;
    }

    @Override
    public AuthorTransformer getTransformer() {
        return authorTransformer;
    }

    @Override
    public PaginationResponse<AuthorDto> findAllAuthorsPaginatedAndFiltered(FilterPaginationRequest<AuthorFilterPaginationRequest> authorFilterPaginationRequest) {
        log.info("AuthorService: findAllAuthorsPaginatedAndFiltered() called");
        return buildPaginationResponse(getDao().findAllAuthorsPaginatedAndFiltered(authorFilterPaginationRequest));
    }
}
