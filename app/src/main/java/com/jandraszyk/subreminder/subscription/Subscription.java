package com.jandraszyk.subreminder.subscription;

import android.graphics.Bitmap;

import java.io.Serializable;

public class Subscription implements Serializable {

    private String subscriptionName;
    private Double subscriptionCost;
    private int subscriptionStartDate;
//    private Bitmap subscriptionImage;
    private int imageResourceNumber;

    public Subscription(String subscriptionName, Double subscriptionCost, int subscriptionStartDate, int imageResourceNumber) {
        this.subscriptionName = subscriptionName;
        this.subscriptionCost = subscriptionCost;
        this.subscriptionStartDate = subscriptionStartDate;
        this.imageResourceNumber = imageResourceNumber;
//        this.subscriptionImage = subscriptionImage;
    }

    public int getImageResourceNumber() {
        return imageResourceNumber;
    }

    public void setImageResourceNumber(int imageResourceNumber) {
        this.imageResourceNumber = imageResourceNumber;
    }

    public String getSubscriptionName() {
        return subscriptionName;
    }

    public void setSubscriptionName(String subscriptionName) {
        this.subscriptionName = subscriptionName;
    }

    public Double getSubscriptionCost() {
        return subscriptionCost;
    }

    public void setSubscriptionCost(Double subscriptionCost) {
        this.subscriptionCost = subscriptionCost;
    }

    public int getSubscriptionStartDate() {
        return subscriptionStartDate;
    }

    public void setSubscriptionStartDate(int subscriptionStartDate) {
        this.subscriptionStartDate = subscriptionStartDate;
    }

//    public Bitmap getSubscriptionImage() {
//        return subscriptionImage;
//    }
//
//    public void setSubscriptionImage(Bitmap subscriptionImage) {
//        this.subscriptionImage = subscriptionImage;
//    }
}
