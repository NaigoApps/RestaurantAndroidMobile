package com.naigoapps.restaurantmobile.tasks;

import com.naigoapps.restaurantmobile.RemoteLoadTask;
import com.naigoapps.restaurantmobile.dto.PhaseDTO;
import com.naigoapps.restaurantmobile.rs.PhasesRS;
import com.naigoapps.restaurantmobile.rs.RSFactory;

import java.io.IOException;

import androidx.core.util.Consumer;
import androidx.fragment.app.FragmentActivity;
import retrofit2.Response;

public class PhasesLoadTask extends RemoteLoadTask<PhaseDTO[]> {

    public PhasesLoadTask(Consumer<PhaseDTO[]> consumer) {
        super(consumer);
    }

    @Override
    protected Response<PhaseDTO[]> makeRequest(FragmentActivity activity) throws IOException{
        PhasesRS pRS = RSFactory.createService(activity, PhasesRS.class);
        return pRS.list().execute();
    }
}