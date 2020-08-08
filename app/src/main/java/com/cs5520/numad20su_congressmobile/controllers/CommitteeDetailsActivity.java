package com.cs5520.numad20su_congressmobile.controllers;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.cs5520.numad20su_congressmobile.R;
import com.cs5520.numad20su_congressmobile.content.models.Committee;

public class CommitteeDetailsActivity extends AppCompatActivity {

//<<<<<<< HEAD
    private Committee committee;
    private TextView title;
    private TextView chamber;
    private TextView chair;
    private TextView website;
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
        website = findViewById(R.id.committee_url);

        Intent openDetailsIntent = getIntent();

        committee = openDetailsIntent.getParcelableExtra("committee");
        title.setText(committee.name);
        chamber.setText(committee.chamber);
        website.setText(committee.url);
        //chair.setText(committee.chair +);
        chairSetup();
    }

    //TODO onclick to look at person details, will need to make api request to do so
    private void chairSetup() {
        if (committee.chair != null && committee.chair_party != null && committee.chair_state != null) {
            String chairText = committee.chair + " (" +
                    committee.chair_party + ", " + committee.chair_state + ")";
            chair.setText(chairText);
        }
        else {
            chair.setText("");
        }

    }

    public void goToWebsite (View v) {
        try {
            Uri urlObj = Uri.parse(committee.url);
            Intent goToLink = new Intent(Intent.ACTION_VIEW, urlObj);
            this.startActivity(goToLink);
        }
        catch (Exception e) {
            Toast.makeText(this, "Invalid Link.", Toast.LENGTH_LONG).show();
        }
    }


    //TODO recyclerview subcommittee? Would need to implement subcommittees in model
}