package com.naigoapps.restaurantmobile.tables.table;

import androidx.core.util.Consumer;
import androidx.fragment.app.FragmentActivity;

import com.naigoapps.restaurantmobile.RemoteLoadTask;
import com.naigoapps.restaurantmobile.dto.OrdinationDTO;
import com.naigoapps.restaurantmobile.rs.OrdinationsRS;
import com.naigoapps.restaurantmobile.rs.RSFactory;

import java.io.IOException;

import retrofit2.Response;

public class OrdinationEditTask extends RemoteLoadTask<OrdinationDTO> {

    private String diningTable;
    private String ordination;
    private OrdinationDTO content;

    public OrdinationEditTask(Consumer<OrdinationDTO> consumer) {
        super(consumer);
    }

    public void setContent(OrdinationDTO content) {
        this.diningTable = content.getDiningTableId();
        this.ordination = content.getUuid();
        this.content = content;
    }

    @Override
    protected Response<OrdinationDTO> makeRequest(FragmentActivity activity) throws IOException {
        return RSFactory.createService(activity, OrdinationsRS.class).edit(diningTable, ordination, content).execute();
    }

}
