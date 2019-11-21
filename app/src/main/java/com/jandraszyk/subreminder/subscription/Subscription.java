package com.jandraszyk.subreminder.subscription;

import android.graphics.Bitmap;

public class Subscription {

    private String subscriptionName;
    private Double subscriptionCost;
    private int subscriptionStartDate;
    private Bitmap subscriptionImage;

    public Subscription(String subscriptionName, Double subscriptionCost, int subscriptionStartDate, Bitmap subscriptionImage) {
        this.subscriptionName = subscriptionName;
        this.subscriptionCost = subscriptionCost;
        this.subscriptionStartDate = subscriptionStartDate;
        this.subscriptionImage = subscriptionImage;
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

    public Bitmap getSubscriptionImage() {
        return subscriptionImage;
    }

    public void setSubscriptionImage(Bitmap subscriptionImage) {
        this.subscriptionImage = subscriptionImage;
    }
}
