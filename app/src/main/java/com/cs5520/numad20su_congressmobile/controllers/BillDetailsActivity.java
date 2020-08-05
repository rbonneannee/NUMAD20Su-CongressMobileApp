package com.cs5520.numad20su_congressmobile.controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.cs5520.numad20su_congressmobile.R;
import com.cs5520.numad20su_congressmobile.content.models.Bill;

public class BillDetailsActivity extends AppCompatActivity {

    private Bill bill;
    private TextView billTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_details);
        billTitle = findViewById(R.id.bill_title);

        Intent openDetailsIntent = getIntent();
        bill = openDetailsIntent.getParcelableExtra("bill");
        billTitle.setText(bill.getTitle());
    }
}