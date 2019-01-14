package com.seg2015.group.homeserviceondemand;

import java.io.Serializable;

public class User implements Serializable {

  private String name;
  private String password;
  private String type;

  public User (String name, String password, String type){
    this.name = name;
    this.password=password;
    this.type = type;
  }

  public  User(){

  }

  public String getName() {
    return name;
  }

  public String getPassword() {
    return password;
  }

  public String getType() {
    return type;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}