package com.naigoapps.restaurantmobile.tables;

import androidx.core.util.Consumer;
import androidx.fragment.app.FragmentActivity;

import com.naigoapps.restaurantmobile.RemoteLoadTask;
import com.naigoapps.restaurantmobile.dto.DiningTableSkeletonDTO;
import com.naigoapps.restaurantmobile.rs.DiningTablesRS;
import com.naigoapps.restaurantmobile.rs.RSFactory;

import java.io.IOException;

import retrofit2.Response;

public class DiningTableEditTask extends RemoteLoadTask<DiningTableSkeletonDTO> {

    private DiningTableSkeletonDTO skeletonDTO;

    public DiningTableEditTask(Consumer<DiningTableSkeletonDTO> consumer) {
        super(consumer);
    }

    public void setDiningTable(DiningTableSkeletonDTO dto){
        this.skeletonDTO = dto;
    }

    @Override
    protected Response<DiningTableSkeletonDTO> makeRequest(FragmentActivity activity) throws IOException {
        return RSFactory.createService(activity, DiningTablesRS.class).edit(skeletonDTO).execute();
    }

}
