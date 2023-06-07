package com.naigoapps.restaurantmobile.viewmodels;

import androidx.core.util.Consumer;

import com.naigoapps.restaurantmobile.RemoteLoadTask;
import com.naigoapps.restaurantmobile.dto.OrdinationDTO;
import com.naigoapps.restaurantmobile.tasks.OrdinationsLoadTask;

public class OrdinationsViewModel extends RemoteDataViewModel<OrdinationDTO[]> {

    private String diningTableUuid;

    public void setDiningTableUuid(String diningTableUuid) {
        this.diningTableUuid = diningTableUuid;
    }

    @Override
    protected RemoteLoadTask<OrdinationDTO[]> createTask(Consumer<OrdinationDTO[]> consumer) {
        return new OrdinationsLoadTask(consumer, diningTableUuid);
    }
}
