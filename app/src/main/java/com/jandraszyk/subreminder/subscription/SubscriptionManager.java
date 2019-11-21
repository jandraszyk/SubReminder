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
        if(day > subscription.getSubscriptionStartDate()) {
            return maxDaysInMonth - (day - subscription.getSubscriptionStartDate());
        } else {
            return subscription.getSubscriptionStartDate() - day;
        }
    }
}
