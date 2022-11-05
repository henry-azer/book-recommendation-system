package com.henry.bookrecommendationsystem.enums;

public enum UserMartialStatus {
    SINGLE("SINGLE"), MARRIED("MARRIED"), IN_RELATIONSHIP("IN_RELATIONSHIP");

    private final String martialStatus;

    UserMartialStatus(String martialStatus) {
        this.martialStatus = martialStatus;
    }
}
