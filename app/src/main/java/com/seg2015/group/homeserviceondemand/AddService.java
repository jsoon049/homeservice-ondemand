package com.seg2015.group.homeserviceondemand;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.util.ArrayList;


public class AddService extends AppCompatActivity {

    ListView listView;
    ServiceAdapter adapter;
    ServiceManager manager;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_service);
        listView = (ListView)findViewById(R.id.add_list);

        manager = ServiceManager.getInstance();

        adapter = new ServiceAdapter(this, manager.getServiceList());


        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, final int position, long id){
                final String item = (String)parent.getItemAtPosition(position);
                final int pos = parent.getPositionForView(view);
                PopupMenu popup = new PopupMenu(AddService.this, view);
                popup.getMenuInflater().inflate(R.menu.add_menu, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getTitle().toString().equals("Add")){
                            Service currentService = manager.getServiceAt(pos);
                            intent = new Intent(AddService.this, ServiceProviderActivity.class);
                            String x = currentService.getService();
                            String y = currentService.getRate();
                            intent.putExtra("name", x);
                            intent.putExtra("rate", y);
                            startActivityForResult(new Intent(AddService.this,AvailabilityActivity.class),1);
                        }
                        return true;
                    }
                });
                popup.show();

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        intent.putStringArrayListExtra("userdata",data.getStringArrayListExtra("userdata"));
        setResult(1, intent);
        finish();
    }

}