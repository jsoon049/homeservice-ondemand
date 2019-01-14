package com.seg2015.group.homeserviceondemand;

import java.util.ArrayList;

public class ServiceManager {

  private static ServiceManager instance = null;
  private ArrayList<Service> serviceList;

  protected ServiceManager() {

    serviceList = new ArrayList<>();

  }

  public static ServiceManager getInstance() {
    if (instance == null) {
      instance = new ServiceManager();
    }
    return instance;
  }

  public ArrayList<Service> getServiceList() {
    return serviceList;
  }

  public Service getServiceAt(int index) {
    return serviceList.get(index);
  }

}