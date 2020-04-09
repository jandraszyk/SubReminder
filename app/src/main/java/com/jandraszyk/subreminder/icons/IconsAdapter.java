package com.jandraszyk.subreminder.icons;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.jandraszyk.subreminder.R;

import java.util.ArrayList;

public class IconsAdapter extends BaseAdapter {
    private Context context;
    TypedArray icons;

    public IconsAdapter(Context context, TypedArray icons) {
        this.context = context;
        this.icons = icons;
    }


    @Override
    public int getCount() {
        return icons.length();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View grid;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(view == null) {
            grid = inflater.inflate(R.layout.icon_single_element, null);
            ImageView iconView = (ImageView) grid.findViewById(R.id.grid_icon);
            iconView.setImageResource(icons.getResourceId(i,-1));
        } else {
            grid  = (View) view;
        }
        return grid;
    }
}
