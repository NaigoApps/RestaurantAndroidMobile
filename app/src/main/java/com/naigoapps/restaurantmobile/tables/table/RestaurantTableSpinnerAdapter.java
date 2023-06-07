package com.naigoapps.restaurantmobile.tables.table;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.naigoapps.restaurantmobile.R;
import com.naigoapps.restaurantmobile.dto.RestaurantTableDTO;
import com.naigoapps.restaurantmobile.widgets.SpinnerAdapter;

public class RestaurantTableSpinnerAdapter extends SpinnerAdapter<RestaurantTableDTO> {

    public RestaurantTableSpinnerAdapter(Context context, int resource) {
        super(context, resource);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = super.getView(position, convertView, parent);
        if (getItem(position).isBusy()) {
            v.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorDanger));
        } else {
            v.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorTransparent));
        }
        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = super.getView(position, convertView, parent);
        if (getItem(position).isBusy()) {
            v.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorDanger));
        } else {
            v.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorTransparent));
        }
        return v;
    }
}
