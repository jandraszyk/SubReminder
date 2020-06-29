package com.jandraszyk.subreminder.subscription;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
        LinearLayout layout;

        ViewHolder(View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.linear_subscription_element);
            subImage = itemView.findViewById(R.id.sub_image);
            subName = itemView.findViewById(R.id.sub_name);
            subCost = itemView.findViewById(R.id.sub_cost);
            subDate = itemView.findViewById(R.id.sub_date);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    final int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION) {
                        final Subscription subscription = subscriptions.get(position);
                        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                                .setTitle("Delete this?")
                                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        subscriptions.remove(subscription);
                                        notifyItemRemoved(position);
                                        notifyItemRangeChanged(position, subscriptions.size());
                                    }
                                })
                                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                });
                        builder.show();
                    }
                    return true;
                }
            });
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

    public List<Subscription> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(List<Subscription> subscriptions) {
        this.subscriptions = subscriptions;
        notifyDataSetChanged();
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
        Bitmap imageBitmap = BitmapFactory.decodeResource(context.getResources(), subscription.getImageResourceNumber());
        imageView.setImageBitmap(imageBitmap);
        Drawable shape = viewHolder.layout.getBackground();
        shape.setTint(subscription.getSubscriptionBackgroundColor());

    }

    @Override
    public int getItemCount() {
        Log.d("SUBS", String.valueOf(subscriptions.size()));
        return subscriptions.size();
    }
}

