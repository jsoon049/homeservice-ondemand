package com.seg2015.group.homeserviceondemand;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ServiceProviderActivity extends AppCompatActivity {

    private ExpandableListView listView;
    private ServiceProviderAdapter serviceAdapter;
    private Service service;
    private ArrayList<Service> services;
    private ArrayList<String> names = new ArrayList<>();
    private HashMap<Service,ArrayList<String>> listHashMap;
    private DatabaseReference databaseUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_provider);

        listView = (ExpandableListView)findViewById(R.id.service_provider);
        services = new ArrayList<>();
        listHashMap = new HashMap<>();
        serviceAdapter = new ServiceProviderAdapter(this, services, listHashMap);
        listView.setAdapter(serviceAdapter);

        databaseUsers = FirebaseDatabase.getInstance().getReference("services");


        listView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener(){
            @Override
            public boolean onGroupClick(ExpandableListView parent, final View view, final int position, long id){
                final String item = (String)parent.getItemAtPosition(position);
                final int pos = parent.getPositionForView(view);

                PopupMenu popup = new PopupMenu(ServiceProviderActivity.this, view);
                popup.getMenuInflater().inflate(R.menu.delete_menu, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getTitle().toString().equals("Delete")){
                            Service currentService = getServiceAt(pos);
                            serviceAdapter.delete(currentService);
                            serviceAdapter.notifyDataSetChanged();
                        }
                        return true;
                    }
                });
                popup.show();
                return true;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.service_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.profile:
                Intent intent = new Intent(this, DisplayProfileActivity.class);
                startActivity(intent);
                return true;
            case R.id.service:
                Intent i = new Intent(this, AddService.class);
                startActivityForResult(i, 1);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 1 && resultCode == 1 && intent != null) {
            service = new Service("","");
            String x = intent.getStringExtra("name");
            String y = intent.getStringExtra("rate");
            ArrayList<String> z = intent.getStringArrayListExtra("userdata");

            if(names.contains(x)){
                Toast toast = Toast.makeText(getApplicationContext(), "Service already added. Try again!", Toast.LENGTH_SHORT);
                toast.show();
            }
            else{
                names.add(x);
                service.setService(x);
                service.setRate(y);
                services.add(service);
                listHashMap.put(services.get(services.size()-1),z);
                listView.expandGroup(services.size()-1);
                serviceAdapter.notifyDataSetChanged();

                String id = databaseUsers.push().getKey();
                databaseUsers.child(id).setValue(new FullService("Arik",service.getService(),service.getRate(),"No rating yet", "No comment yet"));
                DatabaseReference newRef = databaseUsers.child(id);
                //String nId = newRef.push().getKey();
                newRef.child("availabilities").setValue(z);
            }
        }
    }

    public Service getServiceAt(int index) {
        return services.get(index);
    }

}
