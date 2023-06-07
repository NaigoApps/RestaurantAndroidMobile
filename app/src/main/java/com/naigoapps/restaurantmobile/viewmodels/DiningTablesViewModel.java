package com.naigoapps.restaurantmobile.viewmodels;

import com.naigoapps.restaurantmobile.RemoteLoadTask;
import com.naigoapps.restaurantmobile.dto.DiningTableDTO;
import com.naigoapps.restaurantmobile.tasks.DiningTablesLoadTask;

import androidx.core.util.Consumer;

public class DiningTablesViewModel extends RemoteDataViewModel<DiningTableDTO[]> {

    @Override
    protected RemoteLoadTask<DiningTableDTO[]> createTask(Consumer<DiningTableDTO[]> consumer) {
        return new DiningTablesLoadTask(consumer);
    }
}
