package com.naigoapps.restaurantmobile.viewmodels;

import androidx.core.util.Consumer;

import com.naigoapps.restaurantmobile.RemoteLoadTask;
import com.naigoapps.restaurantmobile.dto.PhaseDTO;
import com.naigoapps.restaurantmobile.tasks.PhasesLoadTask;

public class PhasesViewModel extends RemoteDataViewModel<PhaseDTO[]> {

    @Override
    protected RemoteLoadTask<PhaseDTO[]> createTask(Consumer<PhaseDTO[]> consumer) {
        return new PhasesLoadTask(consumer);
    }
}
