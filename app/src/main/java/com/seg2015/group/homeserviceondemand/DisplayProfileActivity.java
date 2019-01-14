package com.seg2015.group.homeserviceondemand;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.w3c.dom.Text;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class DisplayProfileActivity extends AppCompatActivity {

    private Button button;
    private TextView addressText;
    private TextView phoneText;
    private TextView companyText;
    private TextView genDesText;
    private TextView licensedText;
    private boolean found;

    public static ArrayList<MyProfile> profiles;

    static {
        profiles = new ArrayList<>();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_profile);
        loadData();

        TextView addressText = (TextView) findViewById(R.id.addressTextView);
        TextView phoneText = (TextView) findViewById(R.id.phoneTextView);
        TextView companyText = (TextView) findViewById(R.id.companyTextView);
        TextView genDesText = (TextView) findViewById(R.id.genDesTextView);
        TextView licensedText = (TextView) findViewById(R.id.licensedTextView);

        for (MyProfile profile: profiles){
            if (profile.getUserName().equals(MainActivity.currentUserName)){
                addressText.setText(profile.getAddress());
                phoneText.setText(profile.getPhoneNum());
                companyText.setText(profile.getCompany());
                genDesText.setText(profile.getGenDes());
                licensedText.setText(profile.getLicensed());
            }
        }

        button = (Button) findViewById(R.id.editProfile_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEditProfileActivity();
            }
        });
    }

    private void loadData(){

        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("task list", null);

        Type type = new TypeToken<ArrayList<MyProfile>>() {}.getType();
        profiles = gson.fromJson(json, type);

        if (profiles==null){
            profiles = new ArrayList<>();
        }

    }

    public void openEditProfileActivity(){
        Intent intent = new Intent(this, EditProfileActivity.class);
        startActivity(intent);
    }
}
