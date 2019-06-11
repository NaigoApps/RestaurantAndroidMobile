package com.naigoapps.restaurantmobile.viewmodels;

import com.naigoapps.restaurantmobile.RemoteLoadTask;
import com.naigoapps.restaurantmobile.dto.DiningTableDTO;
import com.naigoapps.restaurantmobile.dto.WaiterDTO;
import com.naigoapps.restaurantmobile.tasks.DiningTableLoadTask;
import com.naigoapps.restaurantmobile.tasks.WaitersLoadTask;

import java.util.List;

import androidx.core.util.Consumer;
import androidx.fragment.app.FragmentActivity;

public class DiningTableViewModel extends RemoteViewModel<DiningTableDTO> {

    private String tableUuid;

    public void setTableUuid(String tableUuid) {
        this.tableUuid = tableUuid;
    }

    public String getTableUuid() {
        return tableUuid;
    }

    @Override
    protected RemoteLoadTask<DiningTableDTO> createTask(FragmentActivity owner, Consumer<DiningTableDTO> consumer) {
        return new DiningTableLoadTask(owner, consumer, tableUuid);
    }
}
