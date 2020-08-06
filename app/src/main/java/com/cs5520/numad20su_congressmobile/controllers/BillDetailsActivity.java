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
    private TextView billNumber;
    private TextView billSummary;
    private TextView billIntroduced;
    private TextView billSponsor;
    private TextView billCommittees;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_details);
        billTitle = findViewById(R.id.bill_title);
        billNumber = findViewById(R.id.bill_number);
        billSummary = findViewById(R.id.bill_summary);
        billIntroduced = findViewById(R.id.bill_introduced);
        billSponsor = findViewById(R.id.bill_sponsor);
        billCommittees = findViewById(R.id.bill_committees);


        Intent openDetailsIntent = getIntent();



        bill = openDetailsIntent.getParcelableExtra("bill");
        billNumber.setText(bill.getNumber());
        billTitle.setText(bill.getTitle());
        billSummary.setText(bill.getSummary());
        billIntroduced.setText(bill.getIntroduced_date());
        //billSponsor.setText(bill.getSponsor_name());
        sponsorSetup();
        billCommittees.setText(bill.getCommittees());
    }


    private void sponsorSetup() {
        String sponsorText = bill.getSponsor_title() + " " + bill.getSponsor_name() + " (" +
                bill.getSponsor_party() + ", " + bill.getSponsor_state() + ")";
        billSponsor.setText(sponsorText);

    }
}