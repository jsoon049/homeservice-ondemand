package com.seg2015.group.homeserviceondemand;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.*;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class CreateAccount extends AppCompatActivity {

  private String username;
  private String password;
  private String passwordConfirm;
  private String type;
  private Button button;
  private DatabaseReference databaseUsers;
  private DatabaseReference admin;
  private boolean adminCreated;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_create_account);
    FirebaseApp.initializeApp(this);
    databaseUsers = FirebaseDatabase.getInstance().getReference("users");
    admin = FirebaseDatabase.getInstance().getReference("settings");

    button = (Button)findViewById(R.id.button3);
    adminCreated = false;
  }

  protected void onStart(){
    super.onStart();
    admin.addValueEventListener(new ValueEventListener() {
      @Override
      public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        for (DataSnapshot postSnapshot: dataSnapshot.getChildren()){
          boolean isAdmin = postSnapshot.getValue(java.lang.Boolean.class);
          if (isAdmin){
            adminCreated = true;
          }
        }
      }

      @Override
      public void onCancelled(@NonNull DatabaseError databaseError) {

      }
    });

  }

  public void onClickType(View view){
    final PopupMenu popupMenu = new PopupMenu(getApplicationContext(),button);
    if (adminCreated)
      popupMenu.getMenuInflater().inflate(R.menu.type_woutadmin_menu,popupMenu.getMenu());
    else
      popupMenu.getMenuInflater().inflate(R.menu.type_menu,popupMenu.getMenu());

    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
      @Override
      public boolean onMenuItemClick(MenuItem item) {
        if (item.getTitle().toString().equals("Home Owner"))
          type = "USER";
        if (item.getTitle().toString().equals("Service Provider"))
          type = "PROV";
        if (item.getTitle().toString().equals("Admin"))
          type = "ADMIN";
        return true;
      }
    });
    popupMenu.show();
  }

  public void onClick(View view) {
    username = ((EditText) findViewById(R.id.editText5)).getText().toString();
    password = ((EditText) findViewById(R.id.password)).getText().toString();
    passwordConfirm = ((EditText) findViewById(R.id.passwordConfirm)).getText().toString();

    if (username.length() < 4){
      Toast toast = Toast.makeText(getApplicationContext(), "Username has to be at least 4 characters", Toast.LENGTH_SHORT);
      toast.show();
    }
    else {
      if (password.length() < 5){
        Toast toast = Toast.makeText(getApplicationContext(), "Password must be at least 5 characters", Toast.LENGTH_SHORT);
        toast.show();
      }
      else if (!(password.equals(passwordConfirm))){
        Toast toast = Toast.makeText(getApplicationContext(), "Passwords do not match", Toast.LENGTH_SHORT);
        toast.show();
      }
      else if (type == null){
        Toast toast = Toast.makeText(getApplicationContext(), "Account Type not Chosen", Toast.LENGTH_SHORT);
        toast.show();
      }
      else {
        Toast toast = Toast.makeText(getApplicationContext(), "Account Created!", Toast.LENGTH_SHORT);
        toast.show();
        String id = databaseUsers.push().getKey();
        User user = new User(username,password, type);
        String hashp = password;
        try { hashp = Sha1.hash(password);}
        catch (Exception e){}

        user.setPassword(hashp);
        databaseUsers.child(id).setValue(user);
        if (type.equals("ADMIN")){
          admin.child(admin.push().getKey()) .setValue(true);
        }
        finish();
      }
    }


  }
}