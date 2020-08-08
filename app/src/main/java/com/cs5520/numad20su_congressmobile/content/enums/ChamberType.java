package com.cs5520.numad20su_congressmobile.content.enums;

import androidx.annotation.NonNull;

public enum ChamberType {
    HOUSE("house"),
    SENATE("senate"),
    JOINT("joint");

    private final String chamberName;

    ChamberType(String chamberName) {
        this.chamberName = chamberName;
    }

    public boolean equalsName(String otherName) {
        return chamberName.equals(otherName);
    }

    @NonNull
    public String toString() {
        return this.chamberName;
    }

}
