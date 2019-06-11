package com.naigoapps.restaurantmobile.rs.cache;

import android.content.Context;
import android.util.Log;

import com.naigoapps.restaurantmobile.SimpleTask;
import com.naigoapps.restaurantmobile.dto.RestaurantTableDTO;
import com.naigoapps.restaurantmobile.rs.RSFactory;
import com.naigoapps.restaurantmobile.rs.TablesRS;

import java.io.IOException;
import java.util.List;

import retrofit2.Response;
public class TablesCache extends DTOCache<RestaurantTableDTO> {

    @Override
    protected void loadEntities(Context context) {
        TablesLoadTask task = new TablesLoadTask(context);
        task.execute();
    }

    private class TablesLoadTask extends SimpleTask<Void, List<RestaurantTableDTO>> {

        private Context context;

        TablesLoadTask(Context context) {
            this.context = context;
        }

        @Override
        protected List<RestaurantTableDTO> doInBackground(Void... voids) {
            TablesRS rs = RSFactory.createService(context, TablesRS.class);
            List<RestaurantTableDTO> result = null;
            try {
                Response<List<RestaurantTableDTO>> response = rs.list().execute();
                if (response.isSuccessful()) {
                    onLoadComplete(response.body());
                }
            } catch (IOException ex) {
                Log.e("Network error", ex.getMessage(), ex);
            }
            return result;
        }
    }
}
