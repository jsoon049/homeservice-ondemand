package com.seg2015.group.homeserviceondemand;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class AvailabilityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        final ArrayList<String> availabilities= new ArrayList<String>();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_availability);

        final Spinner dropdown = findViewById(R.id.spinner1);
        final Spinner dropdown1 = findViewById(R.id.spinner2);
        final Spinner dropdown2 = findViewById(R.id.spinner3);
        final Spinner dropdown3 = findViewById(R.id.spinner4);
        final Spinner dropdown4 = findViewById(R.id.spinner5);
        String[] items = new String[]{"Monday", "Tuesday", "Wednesday","Thursday","Friday","Saturday","Sunday"};
        String[] time1 = new String[]{"09","10","11","12","13","14","15","16","17","18"};
        String[] time2 = new String[]{"00","15","30","45"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        ArrayAdapter<String> adapterHours = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, time1);
        ArrayAdapter<String> adapterMin = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, time2);
        dropdown.setAdapter(adapter);
        dropdown1.setAdapter(adapterHours);
        dropdown2.setAdapter(adapterMin);
        dropdown3.setAdapter(adapterHours);
        dropdown4.setAdapter(adapterMin);

        Button back = findViewById(R.id.buttonBack);
        Button save = findViewById(R.id.buttonSave);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(availabilities.isEmpty())) {
                    setResult(1, new Intent(AvailabilityActivity.this, AddService.class).putStringArrayListExtra ("userdata",availabilities));
                    finish();
                }
                else
                    Toast.makeText(getApplicationContext(),"Please Make at least one availability",Toast.LENGTH_SHORT).show();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text1 = dropdown.getSelectedItem().toString();
                String text2 = dropdown1.getSelectedItem().toString();
                String text3 = dropdown2.getSelectedItem().toString();
                String text4 = dropdown3.getSelectedItem().toString();
                String text5 = dropdown4.getSelectedItem().toString();

                String fullText = text1+ " "+text4+"h"+text5+"-"+text2+"h"+text3;

                if (text4.equals(text2) && text3.equals(text5)){
                    Toast.makeText(getApplicationContext(),"Availability not valid, Please do not enter the same times ",Toast.LENGTH_SHORT).show();
                }
                else if (Integer.parseInt(text2) < Integer.parseInt(text4)){
                    Toast.makeText(getApplicationContext(),"Availability not valid, Please enter a hour that makes sense ",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(),fullText,Toast.LENGTH_SHORT).show();
                    availabilities.add(fullText);
                }
            }
        });

    }
    @Override
    public void onBackPressed() {

    }

}
