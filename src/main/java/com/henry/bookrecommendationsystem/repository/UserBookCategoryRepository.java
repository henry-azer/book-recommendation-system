package com.henry.bookrecommendationsystem.repository;

import com.henry.bookrecommendationsystem.entity.UserBookCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Henry Azer
 * @since 10/11/2022
 */
@Repository
public interface UserBookCategoryRepository extends JpaRepository<UserBookCategory, Long> {
    List<UserBookCategory> findAllByUserIdAndMarkedAsDeletedFalse(Long userId);
}