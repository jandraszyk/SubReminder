package com.jandraszyk.subreminder.subscription;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jandraszyk.subreminder.R;

import java.util.Calendar;
import java.util.List;

public class SubscriptionAdapter extends RecyclerView.Adapter<SubscriptionAdapter.ViewHolder> {

    private Context context;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView subImage;
        TextView subName;
        TextView subCost;
        TextView subDate;

        ViewHolder(View itemView) {
            super(itemView);
            subImage = itemView.findViewById(R.id.sub_image);
            subName = itemView.findViewById(R.id.sub_name);
            subCost = itemView.findViewById(R.id.sub_cost);
            subDate = itemView.findViewById(R.id.sub_date);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Animation animation = new AlphaAnimation(0.3f,1.0f);
            animation.setDuration(500);
            view.startAnimation(animation);
            int position = getAdapterPosition();
            if(position != RecyclerView.NO_POSITION) {
                Subscription subscription = subscriptions.get(position);
                Toast.makeText(context, "You clicked " + subscription.getSubscriptionName(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private List<Subscription> subscriptions;

    public SubscriptionAdapter(List<Subscription> subscriptions) {
        this.subscriptions = subscriptions;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View subscriptionView = inflater.inflate(R.layout.subscription_element, viewGroup, false);

        return new ViewHolder(subscriptionView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        SubscriptionManager manager = new SubscriptionManager();
        Subscription subscription = subscriptions.get(i);
        TextView subName = viewHolder.subName;
        subName.setText(subscription.getSubscriptionName());
        TextView subCost = viewHolder.subCost;
        subCost.setText(String.format("%s\tPLN", subscription.getSubscriptionCost()));
        TextView subDate = viewHolder.subDate;
        subDate.setText(String.format("In %s day(s)",manager.calculateDaysUntilNextPayment(subscription, Calendar.getInstance())));
        ImageView imageView = viewHolder.subImage;
        imageView.setImageBitmap(subscription.getSubscriptionImage());

    }

    @Override
    public int getItemCount() {
        return subscriptions.size();
    }
}

