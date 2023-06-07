package com.naigoapps.restaurantmobile.tasks;

import androidx.core.util.Consumer;
import androidx.fragment.app.FragmentActivity;

import com.naigoapps.restaurantmobile.RemoteLoadTask;
import com.naigoapps.restaurantmobile.dto.LocationDTO;
import com.naigoapps.restaurantmobile.rs.LocationsRS;
import com.naigoapps.restaurantmobile.rs.RSFactory;

import java.io.IOException;

import retrofit2.Response;

public class LocationsLoadTask extends RemoteLoadTask<LocationDTO[]> {

    public LocationsLoadTask(Consumer<LocationDTO[]> consumer) {
        super(consumer);
    }

    @Override
    protected Response<LocationDTO[]> makeRequest(FragmentActivity activity) throws IOException{
        LocationsRS lRS = RSFactory.createService(activity, LocationsRS.class);
        return lRS.list().execute();
    }
}