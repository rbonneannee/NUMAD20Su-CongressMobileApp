package com.cs5520.numad20su_congressmobile.controllers;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
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
    billNumber.setText(bill.number);
    billTitle.setText(bill.title);
    billIntroduced.setText(bill.introduced_date);
    sponsorSetup();
    billCommittees.setText(bill.committees);
    urlText = bill.congressdotgov_url + "/text";

    //sampleText.setText("veto: " + bill.vetoed + "\n enacted:" + bill.enacted);
    //bill.vetoed = "yes";
    setUpStatusDiagram();
  }

  private void sponsorSetup() {
    String sponsorText = bill.sponsor_title + " " + bill.sponsor_name + " (" +
        bill.sponsor_party + ", " + bill.sponsor_state + ")";
    billSponsor.setText(sponsorText);

  }

  public void goToPDF(View v) {
    //Uri uriUrl = Uri.parse("https://www.congress.gov/bill/114th-congress/house-bill/1314/text");
    Uri uriUrl = Uri.parse(urlText);
    Intent launchFullText = new Intent(Intent.ACTION_VIEW, uriUrl);
    startActivity(launchFullText);
  }

  private void setUpStatusDiagram() {
    if (bill.house_passage != null) {
      passedHouse.append("\n" + bill.house_passage);
      passedHouse.setBackgroundColor(Color.parseColor("#D3F1B1"));
    }
    if (bill.senate_passage != null) {
      passedSenate.append("\n" + bill.senate_passage);
      passedSenate.setBackgroundColor(Color.parseColor("#D3F1B1"));
    }
    // TODO Account for veto passage
    if (bill.vetoed != null) {
      if (bill.enacted != null) {
        passedPresident.append("\n" + bill.vetoed);
        passedPresident.setBackgroundColor(Color.YELLOW);
      } else {
        passedPresident.append("\n" + bill.vetoed);
        passedPresident.setBackgroundColor(Color.RED);
      }

    }
    if (bill.enacted != null) {
      passedLaw.append("\n" + bill.enacted);
      passedLaw.setBackgroundColor(Color.parseColor("#D3F1B1"));
    }
  }

}