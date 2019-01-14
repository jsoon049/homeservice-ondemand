
package com.seg2015.group.homeserviceondemand;

public class Service {
  private String service;
  private String rate;

  public Service(String service,String rate){
    this.service = service;
    this.rate = rate;
  }

  public String getRate() {
    return rate;
  }

  public String getService() {
    return service;
  }

  public void setRate(String rate) {
    this.rate = rate;
  }

  public void setService(String service) {
    this.service = service;
  }
}