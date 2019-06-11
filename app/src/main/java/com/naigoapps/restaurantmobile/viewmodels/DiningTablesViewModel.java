package com.naigoapps.restaurantmobile.viewmodels;

import com.naigoapps.restaurantmobile.RemoteLoadTask;
import com.naigoapps.restaurantmobile.dto.DiningTableDTO;
import com.naigoapps.restaurantmobile.tasks.DiningTablesLoadTask;

import androidx.core.util.Consumer;
import androidx.fragment.app.FragmentActivity;

public class DiningTablesViewModel extends RemoteViewModel<DiningTableDTO[]> {

    @Override
    protected RemoteLoadTask<DiningTableDTO[]> createTask(FragmentActivity owner, Consumer<DiningTableDTO[]> consumer) {
        return new DiningTablesLoadTask(owner, consumer);
    }
}
