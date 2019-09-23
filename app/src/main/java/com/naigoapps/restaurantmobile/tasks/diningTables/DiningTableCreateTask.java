package com.naigoapps.restaurantmobile.tasks.diningTables;

import androidx.core.util.Consumer;
import androidx.fragment.app.FragmentActivity;

import com.naigoapps.restaurantmobile.RemoteLoadTask;
import com.naigoapps.restaurantmobile.dto.DiningTableSkeletonDTO;
import com.naigoapps.restaurantmobile.rs.DiningTablesRS;
import com.naigoapps.restaurantmobile.rs.RSFactory;

import java.io.IOException;

import retrofit2.Response;

public class DiningTableCreateTask extends RemoteLoadTask<DiningTableSkeletonDTO> {

    private DiningTableSkeletonDTO skeletonDTO;

    public DiningTableCreateTask(Consumer<DiningTableSkeletonDTO> consumer) {
        super(consumer);
    }

    public void setDiningTable(DiningTableSkeletonDTO dto){
        this.skeletonDTO = dto;
    }

    @Override
    protected Response<DiningTableSkeletonDTO> makeRequest(FragmentActivity activity) throws IOException {
        return RSFactory.createService(activity, DiningTablesRS.class).create(skeletonDTO).execute();
    }

}
