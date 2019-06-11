package com.naigoapps.restaurantmobile.tasks;

import com.naigoapps.restaurantmobile.RemoteLoadTask;
import com.naigoapps.restaurantmobile.dto.DiningTableDTO;
import com.naigoapps.restaurantmobile.rs.DiningTablesRS;
import com.naigoapps.restaurantmobile.rs.RSFactory;

import java.io.IOException;

import androidx.core.util.Consumer;
import androidx.fragment.app.FragmentActivity;
import retrofit2.Response;

public class DiningTableEditTask extends RemoteLoadTask<DiningTableDTO> {

    private DiningTableDTO diningTable;

    public DiningTableEditTask(FragmentActivity activity, Consumer<DiningTableDTO> consumer) {
        super(activity, consumer);
    }

    public void setDiningTable(DiningTableDTO diningTable) {
        this.diningTable = diningTable;
    }

    @Override
    protected Response<DiningTableDTO> makeRequest(FragmentActivity activity) throws IOException {
        return RSFactory.createService(activity, DiningTablesRS.class).edit(diningTable.getUuid(), diningTable).execute();
    }

}
