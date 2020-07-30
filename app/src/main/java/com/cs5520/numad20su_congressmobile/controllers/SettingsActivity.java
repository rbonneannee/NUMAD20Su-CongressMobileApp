package com.cs5520.numad20su_congressmobile.controllers;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.cs5520.numad20su_congressmobile.databinding.ActivitySettingsBinding;


public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySettingsBinding activitySettingsBinding = ActivitySettingsBinding.inflate(getLayoutInflater());
        setContentView(activitySettingsBinding.getRoot());
    }

}