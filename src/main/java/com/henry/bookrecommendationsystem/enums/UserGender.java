package com.henry.bookrecommendationsystem.enums;

/**
 * @author Henry Azer
 * @since 04/11/2022
 */
public enum UserGender {
    MALE("MALE"), FEMALE("FEMALE"), OTHERS("OTHERS");

    private final String gender;

    UserGender(String gender) {
        this.gender = gender;
    }
}
