package com.henry.bookrecommendationsystem.entity;

import com.henry.bookrecommendationsystem.entity.base.BaseEntity;
import com.henry.bookrecommendationsystem.enums.BookCategory;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Henry Azer
 * @since 05/11/2022
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "book")
public class Book extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "category", nullable = false)
    private BookCategory category;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "pages_number", nullable = false)
    private Integer pagesNumber;

    @Column(name = "reading_duration", nullable = false)
    private Integer readingDuration;

    @Column(name = "publish_date", nullable = false)
    private Date publishDate;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;
}
