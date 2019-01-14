package com.seg2015.group.homeserviceondemand;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import static com.seg2015.group.homeserviceondemand.DisplayProfileActivity.profiles;

public class EditProfileActivity extends AppCompatActivity {

    private Button button;
    private EditText phoneEdit;
    private EditText addressEdit;
    private EditText companyEdit;
    private EditText genDesEdit;
    private EditText licensedEdit;
    String sPhone;
    String sAddress;
    String sCompany;
    String sGenDes;
    String sLicensed;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        button = (Button) findViewById(R.id.save_button);

        EditText phoneEdit = (EditText) findViewById(R.id.phoneEditText);
        EditText addressEdit = (EditText) findViewById(R.id.addressEditText);
        EditText companyEdit = (EditText) findViewById(R.id.companyEditText);
        EditText genDesEdit = (EditText) findViewById(R.id.genDesEditText);
        EditText licensedEdit = (EditText) findViewById(R.id.licensedEditText);

        for (MyProfile profile: profiles){
            if (profile.getUserName().equals(MainActivity.currentUserName)){
                addressEdit.setText(profile.getAddress());
                phoneEdit.setText(profile.getPhoneNum());
                companyEdit.setText(profile.getCompany());
                genDesEdit.setText(profile.getGenDes());
                licensedEdit.setText(profile.getLicensed());
            }
        }




        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //saveData();
                openDisplayProfileActivity();
            }
        });
    }

    private void saveData(){

        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(profiles);
        editor.putString("task list", json);
        editor.apply();

    }



    public void openDisplayProfileActivity(){
        EditText phoneEdit = (EditText) findViewById(R.id.phoneEditText);
        EditText addressEdit = (EditText) findViewById(R.id.addressEditText);
        EditText companyEdit = (EditText) findViewById(R.id.companyEditText);
        EditText genDesEdit = (EditText) findViewById(R.id.genDesEditText);
        EditText licensedEdit = (EditText) findViewById(R.id.licensedEditText);

        boolean found = false;


        sPhone = phoneEdit.getText().toString();
        sAddress = addressEdit.getText().toString();
        sCompany = companyEdit.getText().toString();
        sGenDes = genDesEdit.getText().toString();
        sLicensed = licensedEdit.getText().toString();

        for (MyProfile profile: profiles){
            if (profile.getUserName().equals(MainActivity.currentUserName)){
                profile.setCompany(sCompany);
                profile.setAddress(sAddress);
                profile.setGenDes(sGenDes);
                profile.setPhoneNum(sPhone);
                profile.setLicensed(sLicensed);
                found = true;
            }
        }

        if(!found){

            MyProfile newProfile = new MyProfile(MainActivity.currentUserName, sAddress, sPhone, sCompany, sGenDes, sLicensed);
            profiles.add(newProfile);
        }

        if( sAddress.isEmpty()){
            Toast.makeText(this, "You did not enter an address", Toast.LENGTH_SHORT).show();
            addressEdit.setError( "" );
            return;

        }
        else if( sPhone.isEmpty()){
            Toast.makeText(this, "You did not enter a phone number", Toast.LENGTH_SHORT).show();
            phoneEdit.setError( "" );
            return;

        }
        else if( sCompany.isEmpty()){
            Toast.makeText(this, "You did not enter a company", Toast.LENGTH_SHORT).show();
            companyEdit.setError( "" );
            return;

        }

        saveData();
        finish();

    }
}
