package com.naigoapps.restaurantmobile.viewmodels;

import androidx.core.util.Consumer;

import com.naigoapps.restaurantmobile.RemoteLoadTask;
import com.naigoapps.restaurantmobile.dto.OrdinationDTO;
import com.naigoapps.restaurantmobile.tasks.OrdinationLoadTask;

public class OrdinationViewModel extends RemoteDataViewModel<OrdinationDTO> {

    private String tableUuid;
    private String ordinationUuid;

    public void setOrdinationUuid(String ordinationUuid) {
        this.ordinationUuid = ordinationUuid;
    }

    public void setTableUuid(String tableUuid) {
        this.tableUuid = tableUuid;
    }

    @Override
    protected RemoteLoadTask<OrdinationDTO> createTask(Consumer<OrdinationDTO> consumer) {
        return new OrdinationLoadTask(consumer, tableUuid, ordinationUuid);
    }
}
