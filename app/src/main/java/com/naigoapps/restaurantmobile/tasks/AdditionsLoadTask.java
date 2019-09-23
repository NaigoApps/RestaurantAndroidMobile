package com.naigoapps.restaurantmobile.tasks;

import androidx.core.util.Consumer;
import androidx.fragment.app.FragmentActivity;

import com.naigoapps.restaurantmobile.RemoteLoadTask;
import com.naigoapps.restaurantmobile.dto.AdditionDTO;
import com.naigoapps.restaurantmobile.rs.AdditionsRS;
import com.naigoapps.restaurantmobile.rs.RSFactory;

import java.io.IOException;

import retrofit2.Response;

public class AdditionsLoadTask extends RemoteLoadTask<AdditionDTO[]> {

    private String dish;

    public AdditionsLoadTask(Consumer<AdditionDTO[]> consumer, String dish) {
        super(consumer);
        this.dish = dish;
    }

    @Override
    protected Response<AdditionDTO[]> makeRequest(FragmentActivity activity) throws IOException{
        AdditionsRS aRS = RSFactory.createService(activity, AdditionsRS.class);
        return aRS.list(dish).execute();
    }
}