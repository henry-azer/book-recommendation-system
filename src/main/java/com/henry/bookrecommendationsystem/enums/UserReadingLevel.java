package com.henry.bookrecommendationsystem.enums;

/**
 * @author Henry Azer
 * @since 10/11/2022
 */
public enum UserReadingLevel {
    BEGINNER("BEGINNER"),
    INTERMEDIATE("INTERMEDIATE"),
    EXPERT("EXPERT");

    private final String level;

    UserReadingLevel(String level) {
        this.level = level;
    }
}
