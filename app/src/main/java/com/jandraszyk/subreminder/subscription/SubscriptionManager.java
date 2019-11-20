package com.jandraszyk.subreminder.subscription;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SubscriptionManager {
    private List<Subscription> subscriptionList;

    public SubscriptionManager() {
        this.subscriptionList = new ArrayList<>();
    }

    public Integer calculateDaysUntilNextPayment(Subscription subscription, Calendar today) {
        int maxDaysInMonth = today.getActualMaximum(Calendar.DAY_OF_MONTH);
        int day = today.get(Calendar.DAY_OF_MONTH);
        if(day > subscription.getSubscribtionStartDate()) {
            return maxDaysInMonth - (day - subscription.getSubscribtionStartDate());
        } else {
            return subscription.getSubscribtionStartDate() - day;
        }
    }
}
