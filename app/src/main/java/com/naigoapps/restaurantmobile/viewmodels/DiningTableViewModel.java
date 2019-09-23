package com.naigoapps.restaurantmobile.viewmodels;

import androidx.core.util.Consumer;

import com.naigoapps.restaurantmobile.RemoteLoadTask;
import com.naigoapps.restaurantmobile.dto.DiningTableDTO;
import com.naigoapps.restaurantmobile.tasks.diningTables.DiningTableLoadTask;

public class DiningTableViewModel extends RemoteDataViewModel<DiningTableDTO> {

    private String tableUuid;

    public void setTableUuid(String tableUuid) {
        this.tableUuid = tableUuid;
    }

    public String getTableUuid() {
        return tableUuid;
    }

    @Override
    protected RemoteLoadTask<DiningTableDTO> createTask(Consumer<DiningTableDTO> consumer) {
        return new DiningTableLoadTask(consumer, tableUuid);
    }
}
