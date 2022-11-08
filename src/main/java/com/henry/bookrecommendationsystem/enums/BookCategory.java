package com.henry.bookrecommendationsystem.enums;

/**
 * @author Henry Azer
 * @since 05/11/2022
 */
public enum BookCategory {
    SCIENCE_FICTION("SCIENCE_FICTION"), HORROR("HORROR"),
    CLASSICS("CLASSICS"), ACTION_ADVENTURE("ACTION_ADVENTURE"),
    ROMANTIC("ROMANTIC"), CHILDREN("CHILDREN"),
    HISTORY("HISTORY"), SPORT("SPORT");

    private final String category;

    BookCategory(String category) {
        this.category = category;
    }
}
