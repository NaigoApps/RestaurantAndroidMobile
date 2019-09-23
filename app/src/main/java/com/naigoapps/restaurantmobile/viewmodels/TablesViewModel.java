package com.naigoapps.restaurantmobile.viewmodels;

import androidx.core.util.Consumer;
import androidx.lifecycle.ViewModelProviders;

import com.naigoapps.restaurantmobile.Application;
import com.naigoapps.restaurantmobile.RemoteLoadTask;
import com.naigoapps.restaurantmobile.dto.RestaurantTableDTO;
import com.naigoapps.restaurantmobile.tasks.RestaurantTablesLoadTask;

import java.util.List;

public class TablesViewModel extends RemoteDataViewModel<List<RestaurantTableDTO>> {

    @Override
    protected RemoteLoadTask<List<RestaurantTableDTO>> createTask(Consumer<List<RestaurantTableDTO>> consumer) {
        return new RestaurantTablesLoadTask(consumer);
    }


    public static TablesViewModel instance() {
        if (Application.getActivity() != null) {
            return ViewModelProviders.of(Application.getActivity()).get(TablesViewModel.class);
        }
        return null;
    }
}
