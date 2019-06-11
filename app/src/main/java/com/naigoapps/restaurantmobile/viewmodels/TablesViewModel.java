package com.naigoapps.restaurantmobile.viewmodels;

import com.naigoapps.restaurantmobile.RemoteLoadTask;
import com.naigoapps.restaurantmobile.dto.RestaurantTableDTO;
import com.naigoapps.restaurantmobile.tasks.RestaurantTablesLoadTask;

import java.util.List;

import androidx.core.util.Consumer;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;

public class TablesViewModel extends RemoteViewModel<List<RestaurantTableDTO>> {

    @Override
    protected RemoteLoadTask<List<RestaurantTableDTO>> createTask(FragmentActivity owner, Consumer<List<RestaurantTableDTO>> consumer) {
        return new RestaurantTablesLoadTask(owner, consumer);
    }

    public static TablesViewModel get(FragmentActivity f){
        return ViewModelProviders.of(f).get(TablesViewModel.class);
    }
}
