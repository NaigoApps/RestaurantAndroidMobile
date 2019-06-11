package com.naigoapps.restaurantmobile.widgets;

import android.widget.ArrayAdapter;

import com.naigoapps.restaurantmobile.viewmodels.RemoteViewModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

public class RemoteSpinnerAdapter<T> extends ArrayAdapter<T> {

    public RemoteSpinnerAdapter(@NonNull FragmentActivity activity, int resource, RemoteViewModel<List<T>> data) {
        super(activity, resource);
        data.get(activity, list -> {
            if (list != null) {
                clear();
                addAll(list);
                notifyDataSetChanged();
            }
        });
    }
}
