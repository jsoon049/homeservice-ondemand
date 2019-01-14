package com.seg2015.group.homeserviceondemand;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class ServiceProviderAdapter extends BaseExpandableListAdapter{

    private final Context context;
    private static ArrayList<Service> serviceProvider;
    private HashMap<Service,ArrayList<String>> listHashMap;

    public ServiceProviderAdapter(Context context, ArrayList<Service> values,HashMap<Service,ArrayList<String>> listHashMap) {
        this.context = context;
        this.serviceProvider = values;
        this.listHashMap = listHashMap;
    }

    @Override
    public View getGroupView(int position, boolean b, View view, ViewGroup viewGroup) {
        Service curService = serviceProvider.get(position);
        if(view == null)
        {
            LayoutInflater inflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_item_layout,null);
        }
        TextView serviceName = (TextView) view.findViewById(R.id.itemName);
        TextView hourlyRate = (TextView) view.findViewById(R.id.itemDescription);

        //Placing content into recipe List Item
        serviceName.setText(curService.getService());
        hourlyRate.setText(curService.getRate());
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        final String childText = (String)getChild(i,i1);
        if(view == null)
        {
            LayoutInflater inflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_time,null);
        }

        TextView txtListChild = (TextView)view.findViewById(R.id.lblListItem);
        txtListChild.setText(childText);
        return view;
    }

    public static void delete(Service currentService) {
        serviceProvider.remove(currentService);
    }

    public void add(Service newService) {
        serviceProvider.add(newService);
    }

    @Override
    public int getGroupCount() {
        return serviceProvider.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return listHashMap.get(serviceProvider.get(i)).size();
    }

    @Override
    public Object getGroup(int i) {
        return serviceProvider.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return listHashMap.get(serviceProvider.get(i)).get(i1); // i = Group Item , i1 = ChildItem
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

}
