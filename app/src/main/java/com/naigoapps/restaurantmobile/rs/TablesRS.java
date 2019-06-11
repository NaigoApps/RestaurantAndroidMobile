package com.naigoapps.restaurantmobile.rs;

import com.naigoapps.restaurantmobile.dto.RestaurantTableDTO;
import com.naigoapps.restaurantmobile.dto.WaiterDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TablesRS {

    @GET("restaurant-tables")
    public Call<List<RestaurantTableDTO>> list();

}

