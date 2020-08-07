package com.cs5520.numad20su_congressmobile.content.enums;

public enum  ChamberType {
    HOUSE ("house"),
    SENATE ("senate"),
    BOTH ("both"),
    JOINT ("joint");

    private final String chamberName;

    private ChamberType(String chamberName) {
        this.chamberName = chamberName;
    }

    public boolean equalsName(String otherName) {
        return chamberName.equals(otherName);
    }

    public String toString() {
        return this.chamberName;
    }

}
