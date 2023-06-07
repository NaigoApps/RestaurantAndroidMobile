package com.naigoapps.restaurantmobile.tasks.diningTables;

import androidx.core.util.Consumer;
import androidx.fragment.app.FragmentActivity;

import com.naigoapps.restaurantmobile.RemoteLoadTask;
import com.naigoapps.restaurantmobile.dto.DiningTableDTO;
import com.naigoapps.restaurantmobile.dto.DiningTableSkeletonDTO;
import com.naigoapps.restaurantmobile.rs.DiningTablesRS;
import com.naigoapps.restaurantmobile.rs.RSFactory;

import java.io.IOException;

import retrofit2.Response;

public class DiningTableSkeletonLoadTask extends RemoteLoadTask<DiningTableSkeletonDTO> {

    private String tableUuid;

    public DiningTableSkeletonLoadTask(Consumer<DiningTableSkeletonDTO> consumer, String tableUuid) {
        super(consumer);
        this.tableUuid = tableUuid;
    }

    @Override
    protected Response<DiningTableSkeletonDTO> makeRequest(FragmentActivity activity) throws IOException{
        DiningTablesRS service = RSFactory.createService(activity, DiningTablesRS.class);
        return service.loadSkeleton(tableUuid).execute();
    }

}
