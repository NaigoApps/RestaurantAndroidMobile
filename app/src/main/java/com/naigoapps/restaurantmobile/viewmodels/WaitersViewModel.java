package com.naigoapps.restaurantmobile.viewmodels;

import com.naigoapps.restaurantmobile.RemoteLoadTask;
import com.naigoapps.restaurantmobile.dto.WaiterDTO;
import com.naigoapps.restaurantmobile.tasks.WaitersLoadTask;

import java.util.List;

import androidx.core.util.Consumer;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;

public class WaitersViewModel extends RemoteViewModel<List<WaiterDTO>> {

    @Override
    protected RemoteLoadTask<List<WaiterDTO>> createTask(FragmentActivity owner, Consumer<List<WaiterDTO>> consumer) {
        return new WaitersLoadTask(owner, consumer);
    }

    public static WaitersViewModel get(FragmentActivity f){
        return ViewModelProviders.of(f).get(WaitersViewModel.class);
    }
}
