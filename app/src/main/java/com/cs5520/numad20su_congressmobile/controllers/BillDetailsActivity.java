package com.cs5520.numad20su_congressmobile.controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.cs5520.numad20su_congressmobile.R;
import com.cs5520.numad20su_congressmobile.content.models.Bill;

public class BillDetailsActivity extends AppCompatActivity {

    private Bill bill;
    private TextView billTitle;
    private TextView billNumber;
    private TextView billIntroduced;
    private TextView billSponsor;
    private TextView billCommittees;
    private TextView testText;
    private String urlText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_details);
        billTitle = findViewById(R.id.bill_title);
        billNumber = findViewById(R.id.bill_number);
        billIntroduced = findViewById(R.id.bill_introduced);
        billSponsor = findViewById(R.id.bill_sponsor);
        billCommittees = findViewById(R.id.bill_committees);
        testText = findViewById(R.id.bill_status);

        Intent openDetailsIntent = getIntent();



        bill = openDetailsIntent.getParcelableExtra("bill");
        billNumber.setText(bill.getNumber());
        billTitle.setText(bill.getTitle());
        billIntroduced.setText(bill.getIntroduced_date());
        //billSponsor.setText(bill.getSponsor_name());
        sponsorSetup();
        billCommittees.setText(bill.getCommittees());
        urlText = bill.getCongressdotgov_url() + "/text";
        //testText.setText(bill.getCongressdotgov_url());
    }


    private void sponsorSetup() {
        String sponsorText = bill.getSponsor_title() + " " + bill.getSponsor_name() + " (" +
                bill.getSponsor_party() + ", " + bill.getSponsor_state() + ")";
        billSponsor.setText(sponsorText);

    }

    public void goToPDF (View v) {
        //Uri uriUrl = Uri.parse("https://www.congress.gov/bill/114th-congress/house-bill/1314/text");
        Uri uriUrl = Uri.parse(urlText);
        Intent launchFullText = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchFullText);
    }
}