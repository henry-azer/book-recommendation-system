package com.henry.bookrecommendationsystem.enums;

/**
 * @author Henry Azer
 * @since 04/11/2022
 */
public enum UserMartialStatus {
    SINGLE("SINGLE"), MARRIED("MARRIED"), IN_RELATIONSHIP("IN_RELATIONSHIP");

    private final String martialStatus;

    UserMartialStatus(String martialStatus) {
        this.martialStatus = martialStatus;
    }
}
