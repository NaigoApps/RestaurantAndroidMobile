package com.naigoapps.restaurantmobile.tables.table;

import androidx.core.util.Consumer;
import androidx.fragment.app.FragmentActivity;

import com.naigoapps.restaurantmobile.RemoteLoadTask;
import com.naigoapps.restaurantmobile.rs.OrdinationsRS;
import com.naigoapps.restaurantmobile.rs.RSFactory;

import java.io.IOException;

import retrofit2.Response;

public class OrdinationDeleteTask extends RemoteLoadTask<Boolean> {

    private String diningTable;
    private String ordination;

    public OrdinationDeleteTask(Consumer<Boolean> consumer) {
        super(consumer);
    }

    public void setDiningTable(String diningTable) {
        this.diningTable = diningTable;
    }

    public void setOrdination(String ordination) {
        this.ordination = ordination;
    }

    @Override
    protected Response<Boolean> makeRequest(FragmentActivity activity) throws IOException {
        return RSFactory.createService(activity, OrdinationsRS.class).delete(diningTable, ordination).execute();
    }

}
