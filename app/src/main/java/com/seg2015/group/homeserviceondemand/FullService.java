package com.seg2015.group.homeserviceondemand;

public class FullService {

    String serviceName;
    String serviceRate;
    String provider;
    String serviceRating;
    String comment;


    public FullService(String provider, String serviceName, String serviceRate, String serviceRating, String comment){
        this.provider = provider;
        this.serviceName = serviceName;
        this.serviceRate = serviceRate;
        this.serviceRating = serviceRating;
        this.comment = comment;
    }

    public FullService(){

    }

    public String getProvider() {
        return provider;
    }

    public String getServiceName() {
        return serviceName;
    }

    public String getServiceRate() {
        return serviceRate;
    }

    public String getServiceRating() {
        return serviceRating;
    }

    public String getComment() { return comment;}

    public String setRate(String rating){
        serviceRating = rating;
        return serviceRating;
    }

    public String setComment(String a){
        comment = a;
        return comment;
    }

}
