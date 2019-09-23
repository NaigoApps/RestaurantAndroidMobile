package com.naigoapps.restaurantmobile.widgets;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.naigoapps.restaurantmobile.R;

import java.util.List;

public class SpinnerAdapter<T> extends ArrayAdapter<T> {

    public SpinnerAdapter(Context context, int resource) {
        super(context, resource);
    }
//
//    @NonNull
//    @Override
//    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        if (position == 0) {
//            TextView view = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.support_simple_spinner_dropdown_item, parent, false);
//            view.setText(R.string.select_one);
//            return view;
//        }
//        return super.getView(position, convertView, parent);
//    }
//
//    @Override
//    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        if (position == 0) {
//            TextView view = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.support_simple_spinner_dropdown_item, parent, false);
//            view.setText(R.string.select_one);
//            return view;
//        }
//        return super.getDropDownView(position, convertView, parent);
//    }

    public void update(List<T> data){
        if (data != null) {
            clear();
//            add(null);
            addAll(data);
            notifyDataSetChanged();
        }
    }
}
