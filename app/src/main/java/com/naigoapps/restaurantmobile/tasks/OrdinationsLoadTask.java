package com.naigoapps.restaurantmobile.tasks;

import androidx.core.util.Consumer;
import androidx.fragment.app.FragmentActivity;

import com.naigoapps.restaurantmobile.RemoteLoadTask;
import com.naigoapps.restaurantmobile.dto.OrdinationDTO;
import com.naigoapps.restaurantmobile.rs.OrdinationsRS;
import com.naigoapps.restaurantmobile.rs.RSFactory;

import java.io.IOException;

import retrofit2.Response;

public class OrdinationsLoadTask extends RemoteLoadTask<OrdinationDTO[]> {

    private String diningTable;

    public OrdinationsLoadTask(Consumer<OrdinationDTO[]> consumer, String diningTable) {
        super(consumer);
        this.diningTable = diningTable;
    }

    @Override
    protected Response<OrdinationDTO[]> makeRequest(FragmentActivity activity) throws IOException {
        OrdinationsRS service = RSFactory.createService(activity, OrdinationsRS.class);
        return service.list(diningTable).execute();
    }

}
