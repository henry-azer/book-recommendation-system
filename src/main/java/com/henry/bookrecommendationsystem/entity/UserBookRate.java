package com.henry.bookrecommendationsystem.entity;

import com.henry.bookrecommendationsystem.entity.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Henry Azer
 * @since 10/11/2022
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user_book_rate", schema = "public")
public class UserBookRate extends BaseEntity {

    @Id
    @SequenceGenerator(name = "user_book_rate_id_sequence", sequenceName = "user_book_rate_id_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_book_rate_id_sequence")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @Column(name = "rate")
    private Integer rate;

    @Column(name = "comment")
    private String comment;
}
