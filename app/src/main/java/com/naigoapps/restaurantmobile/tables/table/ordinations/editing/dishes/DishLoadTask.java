package com.naigoapps.restaurantmobile.tables.table.ordinations.editing.dishes;

import androidx.core.util.Consumer;
import androidx.fragment.app.FragmentActivity;

import com.naigoapps.restaurantmobile.RemoteLoadTask;
import com.naigoapps.restaurantmobile.dto.DishDTO;
import com.naigoapps.restaurantmobile.rs.DishesRS;
import com.naigoapps.restaurantmobile.rs.RSFactory;

import java.io.IOException;

import retrofit2.Response;

public class DishLoadTask extends RemoteLoadTask<DishDTO> {

    private String dish;

    public DishLoadTask(Consumer<DishDTO> consumer) {
        super(consumer);
    }

    public void setDish(String dish) {
        this.dish = dish;
    }

    @Override
    protected Response<DishDTO> makeRequest(FragmentActivity activity) throws IOException{
        DishesRS dishesRS = RSFactory.createService(activity, DishesRS.class);
        return dishesRS.get(dish).execute();
    }
}