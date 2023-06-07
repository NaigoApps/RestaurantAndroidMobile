package com.naigoapps.restaurantmobile.tables.messages;

import androidx.core.util.Consumer;
import androidx.fragment.app.FragmentActivity;

import com.naigoapps.restaurantmobile.RemoteLoadTask;
import com.naigoapps.restaurantmobile.dto.LocationDTO;
import com.naigoapps.restaurantmobile.dto.MessageRequestDTO;
import com.naigoapps.restaurantmobile.rs.LocationsRS;
import com.naigoapps.restaurantmobile.rs.RSFactory;

import java.io.IOException;

import retrofit2.Response;

public class MessagePrintTask extends RemoteLoadTask<Void> {

    private MessageRequestDTO dto;

    public MessagePrintTask(MessageRequestDTO dto, Consumer<Void> consumer) {
        super(consumer);
        this.dto = dto;
    }

    @Override
    protected Response<Void> makeRequest(FragmentActivity activity) throws IOException{
        LocationsRS lRS = RSFactory.createService(activity, LocationsRS.class);
        return lRS.message(dto).execute();
    }
}