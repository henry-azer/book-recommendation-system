package com.henry.bookrecommendationsystem.repository;

import com.henry.bookrecommendationsystem.entity.Book;
import com.henry.bookrecommendationsystem.enums.BookCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * @author Henry Azer
 * @since 07/11/2022
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByAuthorIdAndMarkedAsDeletedFalse(Long authorId);

    @Query("SELECT b FROM Book b WHERE b.markedAsDeleted = :deletedRecords ")
    Page<Book> findAll(Pageable pageable, Boolean deletedRecords);

    @Query("SELECT b FROM Book b WHERE b.markedAsDeleted = :deletedRecords " +
            "AND ((:name) IS NULL OR lower(b.name) like %:name%) " +
            "AND ((:fromPrice) IS NULL OR b.price >= :fromPrice) " +
            "AND ((:toPrice) IS NULL OR b.price <= :toPrice) " +
            "AND ((:fromPagesNumber) IS NULL OR b.pagesNumber >= :fromPagesNumber) " +
            "AND ((:toPagesNumber) IS NULL OR b.pagesNumber <= :toPagesNumber) " +
            "AND ((:fromReadingDuration) IS NULL OR b.readingDuration >= :fromReadingDuration) " +
            "AND ((:toReadingDuration) IS NULL OR b.readingDuration <= :toReadingDuration) " +
            "AND ((:categories) IS NULL OR (b.category IN (:categories))) ")
    Page<Book> findAllBooksPaginatedAndFiltered(String name, Set<BookCategory> categories, Double fromPrice, Double toPrice, Integer fromPagesNumber, Integer toPagesNumber, Integer fromReadingDuration, Integer toReadingDuration, Boolean deletedRecords, Pageable pageable);
}