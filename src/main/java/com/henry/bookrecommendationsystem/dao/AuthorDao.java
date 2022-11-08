package com.henry.bookrecommendationsystem.dao;

import com.henry.bookrecommendationsystem.dao.base.BaseDao;
import com.henry.bookrecommendationsystem.dto.AuthorFilterPaginationRequest;
import com.henry.bookrecommendationsystem.dto.base.pagination.FilterPaginationRequest;
import com.henry.bookrecommendationsystem.entity.Author;
import com.henry.bookrecommendationsystem.repository.AuthorRepository;
import org.springframework.data.domain.Page;

/**
 * @author Henry Azer
 * @since 07/11/2022
 */
public interface AuthorDao extends BaseDao<Author, AuthorRepository> {
    Page<Author> findAllAuthorsPaginatedAndFiltered(FilterPaginationRequest<AuthorFilterPaginationRequest> authorFilterPaginationRequest);
}
