package com.seg2015.group.homeserviceondemand;

public class MyProfile {

    private String userName;
    private String phoneNum;
    private String address;
    private String company;
    private String genDes;
    private String licensed;

    public String getLicensed() {
        return licensed;
    }

    public void setLicensed(String licensed) {
        this.licensed = licensed;
    }

    public MyProfile(String userName, String address, String phoneNum, String company, String genDes, String licensed){
        this.userName = userName;
        this.phoneNum = phoneNum;
        this.address = address;
        this.company = company;
        this.genDes = genDes;
        this.licensed = licensed;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getGenDes() {
        return genDes;
    }

    public void setGenDes(String genDes) {
        this.genDes = genDes;
    }
}
