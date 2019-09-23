package com.naigoapps.restaurantmobile.viewmodels;

import androidx.core.util.Consumer;
import androidx.lifecycle.ViewModelProviders;

import com.naigoapps.restaurantmobile.Application;
import com.naigoapps.restaurantmobile.RemoteLoadTask;
import com.naigoapps.restaurantmobile.dto.WaiterDTO;
import com.naigoapps.restaurantmobile.tasks.WaitersLoadTask;

import java.util.List;

public class WaitersViewModel extends RemoteDataViewModel<List<WaiterDTO>> {

    @Override
    protected RemoteLoadTask<List<WaiterDTO>> createTask(Consumer<List<WaiterDTO>> consumer) {
        return new WaitersLoadTask(consumer);
    }

}
