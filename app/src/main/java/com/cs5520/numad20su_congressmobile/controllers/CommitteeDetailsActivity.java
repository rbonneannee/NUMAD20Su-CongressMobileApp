package com.cs5520.numad20su_congressmobile.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.cs5520.numad20su_congressmobile.R;
import com.cs5520.numad20su_congressmobile.content.models.Committee;

public class CommitteeDetailsActivity extends AppCompatActivity {

  private Committee committee;
  private TextView title;
  private TextView chamber;
  private TextView chair;
  //private TextView
  //private TextView
  //private TextView


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_committee_details);
    title = findViewById(R.id.committee_title);
    chamber = findViewById(R.id.committee_chamber);
    chair = findViewById(R.id.committee_chair);

    Intent openDetailsIntent = getIntent();

    committee = openDetailsIntent.getParcelableExtra("committee");
    title.setText(committee.name);
    chamber.setText(committee.chamber);
    //chair.setText(committee.chair +);
    chairSetup();
  }

  // TODO Write onClick to look at person details
  private void chairSetup() {
    String chairText = committee.chair + " (" +
        committee.chair_party + ", " + committee.chair_state + ")";
    chair.setText(chairText);

  }
}