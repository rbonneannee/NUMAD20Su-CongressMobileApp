package com.cs5520.numad20su_congressmobile.controllers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
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
    private String urlText;

    private TextView sampleText;

    private TextView passedHouse;
    private TextView passedSenate;
    private TextView passedPresident;
    private TextView passedLaw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_details);
        billTitle = findViewById(R.id.bill_title);
        billNumber = findViewById(R.id.bill_number);
        billIntroduced = findViewById(R.id.bill_introduced);
        billSponsor = findViewById(R.id.bill_sponsor);
        billCommittees = findViewById(R.id.bill_committees);

        passedHouse = findViewById(R.id.passed_house);
        passedSenate = findViewById(R.id.passed_senate);
        passedPresident = findViewById(R.id.passed_president);
        passedLaw = findViewById(R.id.became_law);

        sampleText = findViewById(R.id.sample_text);

        Intent openDetailsIntent = getIntent();



        bill = openDetailsIntent.getParcelableExtra("bill");
        billNumber.setText(bill.getNumber());
        billTitle.setText(bill.getTitle());
        billIntroduced.setText(bill.getIntroduced_date());
        sponsorSetup();
        billCommittees.setText(bill.getCommittees());
        urlText = bill.getCongressdotgov_url() + "/text";

        sampleText.setText("veto: " + bill.getVetoed());
        setUpStatusDiagram();
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

    private void setUpStatusDiagram () {
        if (bill.getHouse_passage() != null) {
            passedHouse.setText(bill.getHouse_passage());
            passedHouse.setBackgroundColor(Color.GREEN);
        }
        if (bill.getSenate_passage() != null) {
            passedSenate.setText(bill.getSenate_passage());
            passedSenate.setBackgroundColor(Color.GREEN);
        }
        //TODO account for veto passage
        if (bill.getVetoed() != null) {
            passedPresident.setText(bill.getVetoed());
            passedPresident.setBackgroundColor(Color.RED);
        }
        if (bill.getEnacted() != null) {
            passedLaw.setText(bill.getEnacted());
            passedLaw.setBackgroundColor(Color.GREEN);
        }
    }
}