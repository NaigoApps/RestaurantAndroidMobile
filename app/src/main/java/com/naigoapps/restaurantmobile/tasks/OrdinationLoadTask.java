package com.naigoapps.restaurantmobile.tasks;

import androidx.core.util.Consumer;
import androidx.fragment.app.FragmentActivity;

import com.naigoapps.restaurantmobile.RemoteLoadTask;
import com.naigoapps.restaurantmobile.dto.OrdinationDTO;
import com.naigoapps.restaurantmobile.rs.OrdinationsRS;
import com.naigoapps.restaurantmobile.rs.RSFactory;

import java.io.IOException;

import retrofit2.Response;

public class OrdinationLoadTask extends RemoteLoadTask<OrdinationDTO> {

    private String tableUuid;
    private String ordinationUuid;

    public OrdinationLoadTask(Consumer<OrdinationDTO> consumer, String tableUuid, String ordinationUuid) {
        super(consumer);
        this.tableUuid = tableUuid;
        this.ordinationUuid = ordinationUuid;
    }

    @Override
    protected Response<OrdinationDTO> makeRequest(FragmentActivity activity) throws IOException {
        OrdinationsRS service = RSFactory.createService(activity, OrdinationsRS.class);
        return service.get(tableUuid, ordinationUuid).execute();
    }

}
