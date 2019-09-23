package com.naigoapps.restaurantmobile.tasks.diningTables;

import com.naigoapps.restaurantmobile.RemoteLoadTask;
import com.naigoapps.restaurantmobile.dto.DiningTableDTO;
import com.naigoapps.restaurantmobile.rs.DiningTablesRS;
import com.naigoapps.restaurantmobile.rs.RSFactory;

import java.io.IOException;

import androidx.core.util.Consumer;
import androidx.fragment.app.FragmentActivity;
import retrofit2.Response;

public class DiningTableLoadTask extends RemoteLoadTask<DiningTableDTO> {

    private String tableUuid;

    public DiningTableLoadTask(Consumer<DiningTableDTO> consumer, String tableUuid) {
        super(consumer);
        this.tableUuid = tableUuid;
    }

    @Override
    protected Response<DiningTableDTO> makeRequest(FragmentActivity activity) throws IOException{
        DiningTablesRS service = RSFactory.createService(activity, DiningTablesRS.class);
        return service.load(tableUuid).execute();
    }

}
