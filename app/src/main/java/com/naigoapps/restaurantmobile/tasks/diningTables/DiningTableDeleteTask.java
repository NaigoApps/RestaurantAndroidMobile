package com.naigoapps.restaurantmobile.tasks.diningTables;

import com.naigoapps.restaurantmobile.RemoteLoadTask;
import com.naigoapps.restaurantmobile.rs.DiningTablesRS;
import com.naigoapps.restaurantmobile.rs.RSFactory;

import java.io.IOException;

import androidx.core.util.Consumer;
import androidx.fragment.app.FragmentActivity;
import retrofit2.Response;

public class DiningTableDeleteTask extends RemoteLoadTask<Boolean> {

    private String diningTable;

    public DiningTableDeleteTask(Consumer<Boolean> consumer) {
        super(consumer);
    }

    public void setDiningTable(String diningTable) {
        this.diningTable = diningTable;
    }

    @Override
    protected Response<Boolean> makeRequest(FragmentActivity activity) throws IOException {
        return RSFactory.createService(activity, DiningTablesRS.class).delete(diningTable).execute();
    }

}
