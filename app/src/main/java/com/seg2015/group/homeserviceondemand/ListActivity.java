package com.seg2015.group.homeserviceondemand;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    ListView listView;
    ServiceAdapter adapter;
    ServiceManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        listView = (ListView)findViewById(R.id.list);

        manager = ServiceManager.getInstance();

        adapter = new ServiceAdapter(this, manager.getServiceList());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, final int position, long id){
                final String item = (String)parent.getItemAtPosition(position);
                final int pos = parent.getPositionForView(view);
                //Toast.makeText(getApplicationContext(),pos+"",Toast.LENGTH_SHORT).show();

                PopupMenu popup = new PopupMenu(ListActivity.this, view);
                popup.getMenuInflater().inflate(R.menu.list_menu, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if (item.getTitle().toString().equals("Delete")){
                            Service currentService = manager.getServiceAt(pos);
                            adapter.delete(currentService);
                        }
                        if (item.getTitle().toString().equals("Modify")){
                            Service service = manager.getServiceAt(pos);
                            String serviceName  = service.getService();
                            Intent i = new Intent(ListActivity.this, ListEditorActivity.class);
                            i.putExtra("name", serviceName);
                            startActivity(i);
                        }
                        return true;
                    }
                });
                popup.show();

            }
        });
    }

    public void addToList(View view){
        Intent launchListEditor = new Intent(getApplicationContext(), ListEditorActivity.class);
        startActivityForResult(launchListEditor, 0);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        adapter.notifyDataSetChanged();
        listView.refreshDrawableState();
    }

}