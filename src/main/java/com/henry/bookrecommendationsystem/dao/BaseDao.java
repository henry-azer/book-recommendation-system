package com.henry.bookrecommendationsystem.dao;

import com.henry.bookrecommendationsystem.dto.base.pagination.SortingBy;
import com.henry.bookrecommendationsystem.dto.base.pagination.SortingDirection;
import com.henry.bookrecommendationsystem.dto.base.request.PaginationRequest;
import com.henry.bookrecommendationsystem.entity.BaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public interface BaseDao<Entity extends BaseEntity, Repository extends JpaRepository<Entity, Long>> {

    Repository getRepository();

    default List<Entity> findAll() {
        return getRepository().findAll();
    }

    default Page<Entity> findAllUsersPaginatedRequest(PaginationRequest paginationRequest) {
        PageRequest pageRequest = PageRequest.of(paginationRequest.getPageNumber() - 1, paginationRequest.getPageSize(), buildSort(paginationRequest));
        if (paginationRequest.getPageSize() == -1) paginationRequest.setPageSize(Integer.MAX_VALUE);
        return getRepository().findAll(pageRequest);
    }

    default Optional<Entity> findById(Long id) {
        return getRepository().findById(id);
    }

    default Entity create(Entity entity) {
        return getRepository().save(entity);
    }

    default Entity update(Entity entity) {
        return getRepository().save(entity);
    }

    default void deleteById(Long id) {
        getRepository().deleteById(id);
    }

    default Sort buildSort(PaginationRequest paginationRequest) {
        return Sort.by(paginationRequest.getSortingByList().stream().map(sortingBy
                -> sortingBy.getIsNumber() ? new Sort.Order(sortingBy.getDirection().equals(SortingDirection.ASC)
                ? Sort.Direction.ASC
                : Sort.Direction.DESC, sortingBy.getFieldName())
                : new Sort.Order(sortingBy.getDirection().equals(SortingDirection.ASC)
                ? Sort.Direction.ASC
                : Sort.Direction.DESC, sortingBy.getFieldName()).ignoreCase()).collect(Collectors.toList()));
    }

    default <T extends Class<Entity>> Sort buildSort(PaginationRequest paginationRequest, T entityCls) {
        List<SortingBy> sortingByList = paginationRequest.getSortingByList();
        if (sortingByList == null) {
            sortingByList = new ArrayList<>();
            sortingByList.add(new SortingBy("id", SortingDirection.ASC));
        }
        return Sort.by(sortingByList.stream().map(sortingBy -> {
            Field orderField = ReflectionUtils.findField(entityCls, sortingBy.getFieldName());
            if (orderField == null) throw new NullPointerException("MALFORMED_JSON_REQUEST");
            if (orderField.getType().equals(String.class))
                return new Sort.Order(sortingBy.getDirection().equals(SortingDirection.ASC) ? Sort.Direction.ASC : Sort.Direction.DESC, sortingBy.getFieldName()).ignoreCase();
            return new Sort.Order(sortingBy.getDirection().equals(SortingDirection.ASC) ? Sort.Direction.ASC : Sort.Direction.DESC, sortingBy.getFieldName());
        }).collect(Collectors.toList()));
    }
}