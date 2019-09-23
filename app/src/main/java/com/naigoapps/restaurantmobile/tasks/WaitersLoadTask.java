package com.naigoapps.restaurantmobile.tasks;

import com.naigoapps.restaurantmobile.RemoteLoadTask;
import com.naigoapps.restaurantmobile.dto.WaiterDTO;
import com.naigoapps.restaurantmobile.rs.RSFactory;
import com.naigoapps.restaurantmobile.rs.WaitersRS;

import java.io.IOException;
import java.util.List;

import androidx.core.util.Consumer;
import androidx.fragment.app.FragmentActivity;
import retrofit2.Response;

public class WaitersLoadTask extends RemoteLoadTask<List<WaiterDTO>> {

    public WaitersLoadTask(Consumer<List<WaiterDTO>> consumer) {
        super(consumer);
    }

    @Override
    protected Response<List<WaiterDTO>> makeRequest(FragmentActivity activity) throws IOException{
        WaitersRS waitersRS = RSFactory.createService(activity, WaitersRS.class);
        return waitersRS.list().execute();
    }
}