package com.cs5520.numad20su_congressmobile.controllers;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.cs5520.numad20su_congressmobile.R;
import com.cs5520.numad20su_congressmobile.content.models.Member;

//TODO: add phone number, maybe add district?
public class MemberDetailsActivity extends AppCompatActivity {

    private Member member;
    private TextView name;
    private TextView partyState;
    private TextView website;
    private TextView phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_details);

        name = findViewById(R.id.member_name);
        partyState = findViewById(R.id.member_party_state);
        website = findViewById(R.id.member_link);
        phone = findViewById(R.id.member_phone);

        Intent openDetailsIntent = getIntent();

        member = openDetailsIntent.getParcelableExtra("member");
        createName();
        createPartyState();
        website.setText(member.url);
        phone.setText(member.phone);
    }

    private void createName() {
        String nameString = member.short_title + " " + member.first_name + " " + member.last_name;
        name.setText(nameString);
    }

    private void createPartyState() {
        String partyStateString = member.party + ", " + member.state;
        partyState.setText(partyStateString);
    }

    public void goToWebsite(View v) {
        try {
            Uri urlObj = Uri.parse(member.url);
            Intent goToLink = new Intent(Intent.ACTION_VIEW, urlObj);
            this.startActivity(goToLink);
        } catch (Exception e) {
            Toast.makeText(this, "Invalid Link.", Toast.LENGTH_LONG).show();
        }
    }
}