package com.naigoapps.restaurantmobile.rs.cache;

import android.content.Context;
import android.util.Log;

import com.naigoapps.restaurantmobile.SimpleTask;
import com.naigoapps.restaurantmobile.dto.WaiterDTO;
import com.naigoapps.restaurantmobile.rs.RSFactory;
import com.naigoapps.restaurantmobile.rs.WaitersRS;

import java.io.IOException;
import java.util.List;

import retrofit2.Response;

public class WaitersCache extends DTOCache<WaiterDTO> {

    @Override
    protected void loadEntities(Context context) {
        WaitersLoadTask task = new WaitersLoadTask(context);
        task.execute();
    }

    private class WaitersLoadTask extends SimpleTask<Void, List<WaiterDTO>> {

        private Context context;

        WaitersLoadTask(Context context) {
            this.context = context;
        }

        @Override
        protected List<WaiterDTO> doInBackground(Void... voids) {
            WaitersRS rs = RSFactory.createService(context, WaitersRS.class);
            List<WaiterDTO> result = null;
            try {
                Response<List<WaiterDTO>> response = rs.list().execute();
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
