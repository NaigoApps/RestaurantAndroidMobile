package com.naigoapps.restaurantmobile.tasks;

import com.naigoapps.restaurantmobile.RemoteLoadTask;
import com.naigoapps.restaurantmobile.dto.RestaurantTableDTO;
import com.naigoapps.restaurantmobile.rs.RSFactory;
import com.naigoapps.restaurantmobile.rs.TablesRS;

import java.io.IOException;
import java.util.List;

import androidx.core.util.Consumer;
import androidx.fragment.app.FragmentActivity;
import retrofit2.Response;

public class RestaurantTablesLoadTask extends RemoteLoadTask<List<RestaurantTableDTO>> {

    public RestaurantTablesLoadTask(Consumer<List<RestaurantTableDTO>> consumer) {
        super(consumer);
    }

    @Override
    protected Response<List<RestaurantTableDTO>> makeRequest(FragmentActivity activity) throws IOException{
        TablesRS tablesRS = RSFactory.createService(activity, TablesRS.class);
        return tablesRS.list().execute();
    }
}