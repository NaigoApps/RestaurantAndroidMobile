package com.naigoapps.restaurantmobile.tables.table;

import androidx.core.util.Consumer;
import androidx.fragment.app.FragmentActivity;

import com.naigoapps.restaurantmobile.RemoteLoadTask;
import com.naigoapps.restaurantmobile.rs.OrdinationsRS;
import com.naigoapps.restaurantmobile.rs.RSFactory;

import java.io.IOException;

import retrofit2.Response;

public class OrdinationPrintTask extends RemoteLoadTask<Void> {

    private String diningTable;
    private String ordination;

    public OrdinationPrintTask(Consumer<Void> consumer) {
        super(consumer);
    }

    public void setDiningTable(String diningTable) {
        this.diningTable = diningTable;
    }

    public void setOrdination(String ordination) {
        this.ordination = ordination;
    }

    @Override
    protected Response<Void> makeRequest(FragmentActivity activity) throws IOException {
        return RSFactory.createService(activity, OrdinationsRS.class).print(diningTable, ordination).execute();
    }

}
