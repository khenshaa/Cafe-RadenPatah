package com.alkhensha.cafe_uas;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.alkhensha.cafe_uas.Model.Menu;
import com.alkhensha.cafe_uas.Model.Order2;

import java.util.List;

/**
 * Created by khenshaa on 2/6/18.
 */

public class OrderSpinnerAdapter extends ArrayAdapter<Order2> {


    private Context context;
    private List<Order2> mValues;

    public OrderSpinnerAdapter(Context context, int textViewResourceId, List<Order2> objects){
        super(context, textViewResourceId, objects);
        this.context = context;
        this.mValues = objects;
    }

    @Override
    public int getCount(){
        return mValues.size();
    }
    public Order2 getItem(int position){
        return mValues.get(position);
    }
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //This is for the first item before dropdown or default state.
        TextView label = new TextView(context);
        label.setTextColor(Color.BLACK);
        label.setTextSize(20);
        label.setText(" " + mValues.get(position).getNotable());
        label.setHeight(80);
        label.setGravity(Gravity.LEFT | Gravity.CENTER );
        return label;
    }

    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        //This is when user click the spinner and list of item display
        // beneath it
        TextView label = new TextView(context);
        label.setTextColor(Color.BLACK);
        label.setTextSize(20);
        label.setText(" " + mValues.get(position).getNotable());
        label.setHeight(80);
        label.setGravity(Gravity.LEFT | Gravity.CENTER );

        return label;
    }

}
