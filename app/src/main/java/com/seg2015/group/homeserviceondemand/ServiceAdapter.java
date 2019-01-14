package com.seg2015.group.homeserviceondemand;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;;

public class ServiceAdapter extends ArrayAdapter<Service> {

    private final Context context;
    private final ArrayList<Service> services;

    public ServiceAdapter(Context context, ArrayList<Service> values) {
        super(context, R.layout.list_item_layout, values);
        this.context = context;
        this.services = values;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        //Getting Recipe
        Service curService = services.get(position);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.list_item_layout, parent, false);
        TextView serviceName = (TextView) rowView.findViewById(R.id.itemName);
        TextView hourlyRate = (TextView) rowView.findViewById(R.id.itemDescription);

        //Placing content into recipe List Item
        serviceName.setText(curService.getService());
        hourlyRate.setText((curService.getRate()));

        return rowView;
    }
    @Override
    public void add(Service newService) {
        services.add(newService);
        notifyDataSetChanged();
    }

    public void delete(Service currentService) {
        services.remove(currentService);
        notifyDataSetChanged();
    }
}
