package com.naigoapps.restaurantmobile.viewmodels;

import androidx.core.util.Consumer;

import com.naigoapps.restaurantmobile.RemoteLoadTask;
import com.naigoapps.restaurantmobile.dto.DiningTableSkeletonDTO;
import com.naigoapps.restaurantmobile.tasks.diningTables.DiningTableSkeletonLoadTask;

public class DiningTableSkeletonViewModel extends RemoteDataViewModel<DiningTableSkeletonDTO> {

    private String tableUuid;

    public void setTableUuid(String tableUuid) {
        this.tableUuid = tableUuid;
    }

    public String getTableUuid() {
        return tableUuid;
    }

    @Override
    protected RemoteLoadTask<DiningTableSkeletonDTO> createTask(Consumer<DiningTableSkeletonDTO> consumer) {
        if(tableUuid != null) {
            return new DiningTableSkeletonLoadTask(consumer, tableUuid);
        }
        return null;
    }
}
