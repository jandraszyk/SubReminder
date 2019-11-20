package com.jandraszyk.subreminder;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.jandraszyk.subreminder.subscription.Subscription;
import com.jandraszyk.subreminder.subscription.SubscriptionAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView subscriptionListView;
    private List<Subscription> subscriptionList;
    private SubscriptionAdapter subscriptionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        RecyclerView rvSubscriptions = findViewById(R.id.subscriptions_recycler_view);
        initializeList();
        subscriptionAdapter = new SubscriptionAdapter(subscriptionList);
        rvSubscriptions.setAdapter(subscriptionAdapter);
        rvSubscriptions.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    private void initializeList(){
        subscriptionList = new ArrayList<>();
        subscriptionList.add(new Subscription("SPOTIFY", 19.99, 14, BitmapFactory.decodeResource(getResources(),R.drawable.spotify)));
        subscriptionList.add(new Subscription("Xbox Live Gold", 29.0, 22, BitmapFactory.decodeResource(getResources(),R.drawable.xbox)));
        subscriptionList.add(new Subscription("Xbox Game Pass", 40.0, 29, BitmapFactory.decodeResource(getResources(),R.drawable.xbox)));
        subscriptionList.add(new Subscription("HBO GO", 19.90, 15, BitmapFactory.decodeResource(getResources(),R.drawable.hbo)));
        subscriptionList.add(new Subscription("PlayStation Plus", 64.0, 31, BitmapFactory.decodeResource(getResources(),R.drawable.playstation)));
        subscriptionList.add(new Subscription("Inea Internet", 70.0, 1, BitmapFactory.decodeResource(getResources(),R.drawable.inea)));
        subscriptionList.add(new Subscription("ZUS", 150.0, 1, BitmapFactory.decodeResource(getResources(),R.drawable.zus)));
        subscriptionList.add(new Subscription("iCloud", 3.99, 24, BitmapFactory.decodeResource(getResources(),R.drawable.icloud)));
        subscriptionList.add(new Subscription("Netflix", 54.00, 29, BitmapFactory.decodeResource(getResources(),R.drawable.netflix)));
    }
}
