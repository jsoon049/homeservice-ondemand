package com.seg2015.group.homeserviceondemand;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class WelcomeActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_welcome);

    User model = (User) getIntent().getSerializableExtra("userClass");

    String text = model.getName();
    String type = model.getType();

    if (type.equals("USER"))
      type = "Home Owner";
    if (type.equals("PROV"))
      type = "Service Provider";
    if (type.equals("ADMIN")) {
      type = "Admin";
    }

    TextView textView_name = (TextView) findViewById(R.id.textView_name);

    textView_name.setText(text + "! You're account is type :"+ type + " ");


  }
}