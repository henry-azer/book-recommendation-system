package com.henry.bookrecommendationsystem.enums;

public enum UserGender {
    MALE("MALE"), FEMALE("FEMALE"), OTHERS("OTHERS");

    private final String gender;

    UserGender(String gender) {
        this.gender = gender;
    }
}
