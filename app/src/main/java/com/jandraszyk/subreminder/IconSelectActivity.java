package com.jandraszyk.subreminder;

import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.drawable.Icon;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.jandraszyk.subreminder.icons.IconsAdapter;

public class IconSelectActivity extends AppCompatActivity {

    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_icon_select);
        final TypedArray icons = getResources().obtainTypedArray(R.array.icons);
        IconsAdapter adapter = new IconsAdapter(IconSelectActivity.this,icons);
        gridView = (GridView) findViewById(R.id.icons_grid);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent();
                intent.putExtra("ICON", icons.getResourceId(i, -1));
                setResult(3, intent);
                finish();
            }
        });
    }
}
