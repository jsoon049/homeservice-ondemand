package com.seg2015.group.homeserviceondemand;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Button;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;


public class RatingActivity extends AppCompatActivity {

    private RatingBar rates;
    private EditText userComment;
    private Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

        rates = (RatingBar) findViewById(R.id.ratingBar);
        userComment = (EditText) findViewById(R.id.comments);
        save = (Button) findViewById(R.id.button1);
        rates.setRating(5);
        rates.setMax(5);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (userComment.getText().toString().isEmpty()) {
                    Toast.makeText(RatingActivity.this, "Please fill in feedback text box", Toast.LENGTH_LONG).show();
                }
                else{
                    String rating = String.valueOf(rates.getRating());
                    String x = userComment.getText().toString();
                    Intent intent = getIntent();
                    String position = intent.getStringExtra("position");
                    intent.putExtra("rating", rating);
                    intent.putExtra("comment", x);
                    intent.putExtra("position", position);
                    setResult(1,intent);
                    finish();
                }
            }
        });
    }


}
