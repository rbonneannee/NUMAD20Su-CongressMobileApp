package com.cs5520.numad20su_congressmobile.controllers;

import android.content.Intent;
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
    billNumber.setText(bill.number);
    billTitle.setText(bill.title);
    billIntroduced.setText(bill.introduced_date);
    //billSponsor.setText(bill.getSponsor_name());
    sponsorSetup();
    billCommittees.setText(bill.committees);
    urlText = bill.congressdotgov_url + "/text";
    //testText.setText(bill.getCongressdotgov_url());
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
}