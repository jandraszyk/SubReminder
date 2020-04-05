package com.jandraszyk.subreminder;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jandraszyk.subreminder.subscription.Subscription;
import com.jandraszyk.subreminder.subscription.SubscriptionAdapter;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Subscription> subscriptionList;
    private SubscriptionAdapter subscriptionAdapter;
    private RecyclerView rvSubscriptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("TAG", "onCreate fired");
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        loadData();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
                startActivityForResult(new Intent(MainActivity.this, NewSubscriptionActivity.class),2);
                overridePendingTransition(R.anim.slide_in_up,R.anim.slide_out_up);
            }
        });

        rvSubscriptions = findViewById(R.id.subscriptions_recycler_view);
        initializeList();
        subscriptionAdapter = new SubscriptionAdapter(subscriptionList);
        rvSubscriptions.setAdapter(subscriptionAdapter);
        rvSubscriptions.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        Log.d("TAG", "onActivityResult fired");
        updateData();
        subscriptionAdapter.notifyDataSetChanged();
        if(requestCode == 2) {
            String subName = data.getStringExtra("NAME");
            Double subCost = data.getDoubleExtra("COST",0.0);
            int startDate = data.getIntExtra("DATE",1);
            int subColor = data.getIntExtra("COLOR", Color.YELLOW);
            Subscription subscription = new Subscription(subName,subCost,startDate, R.drawable.xbox, subColor);
            if(subscriptionList != null) {
                subscriptionList.add(subscription);
            } else {
                subscriptionList = new ArrayList<>();
                subscriptionList.add(subscription);
            }
            subscriptionAdapter.notifyItemInserted(subscriptionList.size()-1);
        }
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
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("TAG", "onPaused fired");
        saveData();
    }

    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPreferences",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String jsonSubs = gson.toJson(subscriptionList);
        Log.d("TAG",jsonSubs);
        editor.putString("SUBS",jsonSubs);
        editor.apply();
    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPreferences",MODE_PRIVATE);
        Type type = new TypeToken<ArrayList<Subscription>>(){}.getType();
        Gson gson = new Gson();
        String json = sharedPreferences.getString("SUBS", null);
        subscriptionList = gson.fromJson(json, type);
        if(subscriptionList == null) {
            subscriptionList = new ArrayList<>();
        }
        Log.d("TAG", "Loaded list: " + subscriptionList.toString());
    }

    private void updateData() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPreferences",MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("SUBS", null);
        Type type = new TypeToken<ArrayList<Subscription>>(){}.getType();
        ArrayList<Subscription> temp = gson.fromJson(json, type);
        subscriptionList = temp;
        subscriptionAdapter.setSubscriptions(temp);
        subscriptionAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("TAG", "onStart fired");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("TAG", "onRestart fired");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("TAG", "onResume fired");
//        updateData();

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("TAG", "onStop fired");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("TAG", "onDestroy fired");
    }
}
