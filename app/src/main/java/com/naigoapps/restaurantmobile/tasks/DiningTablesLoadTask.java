package com.naigoapps.restaurantmobile.tasks;

import com.naigoapps.restaurantmobile.RemoteLoadTask;
import com.naigoapps.restaurantmobile.dto.DiningTableDTO;
import com.naigoapps.restaurantmobile.rs.DiningTablesRS;
import com.naigoapps.restaurantmobile.rs.RSFactory;

import java.io.IOException;

import androidx.core.util.Consumer;
import androidx.fragment.app.FragmentActivity;
import retrofit2.Response;

public class DiningTablesLoadTask extends RemoteLoadTask<DiningTableDTO[]> {

    public DiningTablesLoadTask(FragmentActivity activity, Consumer<DiningTableDTO[]> consumer) {
        super(activity, consumer);
    }

    @Override
    protected Response<DiningTableDTO[]> makeRequest(FragmentActivity activity) throws IOException {
        DiningTablesRS service = RSFactory.createService(activity, DiningTablesRS.class);
        return service.list().execute();
    }

}
