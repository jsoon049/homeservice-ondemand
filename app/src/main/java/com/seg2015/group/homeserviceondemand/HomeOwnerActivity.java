package com.seg2015.group.homeserviceondemand;

import android.content.Intent;
import android.graphics.Color;
import android.media.Rating;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.PopupMenu;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class HomeOwnerActivity extends AppCompatActivity {

    private ExpandableListView listView;
    private HomeOwnerAdapter serviceAdapter;
    private FullService service;
    private ArrayList<FullService> services;
    private HashMap<FullService,ArrayList<String>> listHashMap;
    private DatabaseReference databaseUsers;
    private boolean flag = true;

    /* Searched Stuff

     */
    private HomeOwnerAdapter searchedAdapter;
    private ArrayList<FullService> searchedServices;
    private HashMap<FullService,ArrayList<String>> searchedMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_owner);

        databaseUsers = FirebaseDatabase.getInstance().getReference("services");


        listView = (ExpandableListView)findViewById(R.id.allList);
        services = new ArrayList<>();
        listHashMap = new HashMap<>();

        searchedServices= new ArrayList<>();
        searchedMap = new HashMap<>();

        serviceAdapter = new HomeOwnerAdapter(HomeOwnerActivity.this, services, listHashMap);
        listView.setAdapter(serviceAdapter);

        listView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener(){
            @Override
            public boolean onGroupClick(ExpandableListView parent, final View view, final int position, long id){
                final String item = (String)parent.getItemAtPosition(position);
                final int pos = parent.getPositionForView(view);

                PopupMenu popup = new PopupMenu(HomeOwnerActivity.this, view);
                popup.getMenuInflater().inflate(R.menu.rate_menu, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getTitle().toString().equals("Rate")){
                            Intent i = new Intent(HomeOwnerActivity.this, RatingActivity.class);
                            i.putExtra("position",String.valueOf(pos));
                            startActivityForResult(i, 1);
                        }
                        return true;
                    }
                });
                popup.show();
                return true;
            }
        });
        listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                final String item = (String)parent.getItemAtPosition(childPosition);
                final int pos = parent.getPositionForView(v);
                final View w = v;

                PopupMenu popup = new PopupMenu(HomeOwnerActivity.this, v);
                popup.getMenuInflater().inflate(R.menu.book_menu, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getTitle().toString().equals("Book")){
                            w.setBackgroundColor((Color.parseColor("#edea4b")));
                            Toast toast = Toast.makeText(getApplicationContext(), "Service Booked!", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                        return true;
                    }
                });
                popup.show();
                return true;
            }
        });

        SearchView searchBar = (SearchView) findViewById(R.id.searchBar);
        searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //Toast toast = Toast.makeText(getApplicationContext(), query, Toast.LENGTH_SHORT);
                //toast.show();
                searchedServices.clear();
                for (int i = 0; i < services.size(); i ++){
                    if (services.get(i).getServiceName().contains(query) || services.get(i).getServiceRating().contains(query)){
                        searchedServices.add(services.get(i));
                    }
                }
                searchedAdapter = new HomeOwnerAdapter(HomeOwnerActivity.this, searchedServices, listHashMap);
                listView.setAdapter(searchedAdapter);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //Toast toast = Toast.makeText(getApplicationContext(),newText, Toast.LENGTH_SHORT);
                //toast.show();
                return false;
            }
        });
    }

    protected void onStart(){
        super.onStart();
        databaseUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()){
                    ArrayList<String> z = new ArrayList<>();
                    FullService service = postSnapshot.getValue(FullService.class);
                    if(flag ==true){
                        services.add(service);
                    }
                    for (DataSnapshot postSnapshot1 : postSnapshot.child("availabilities").getChildren())
                    {
                        String time = postSnapshot1.getValue(String.class);
                        z.add(time);

                    }
                    listHashMap.put(services.get(services.size()-1),z);
                    listView.expandGroup(services.size()-1);
                }
                serviceAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public FullService getFullServiceAt(int position){
        return services.get(position);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 1 && resultCode == 1 && intent != null) {
            String rates = intent.getStringExtra("rating");
            String comments = intent.getStringExtra("comment");
            String positions = intent.getStringExtra("position");
            int result = Integer.parseInt(positions);
            getFullServiceAt(result).setRate(rates);
            getFullServiceAt(result).setComment(comments);
            flag = false;
            serviceAdapter.notifyDataSetChanged();
            listView.refreshDrawableState();
        }
    }

}
