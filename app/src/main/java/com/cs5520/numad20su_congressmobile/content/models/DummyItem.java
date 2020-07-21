package com.cs5520.numad20su_congressmobile.content.models;

import androidx.annotation.NonNull;

/**
 * A dummy item representing a piece of content.
 */
public class DummyItem {
    public final String id;
    public final String content;
    public final String details;

    public DummyItem(String id, String content, String details) {
        this.id = id;
        this.content = content;
        this.details = details;
    }

    @NonNull
    @Override
    public String toString() {
        return content;
    }
}