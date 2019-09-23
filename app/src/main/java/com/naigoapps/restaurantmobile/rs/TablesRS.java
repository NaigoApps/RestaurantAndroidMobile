package com.naigoapps.restaurantmobile.rs;

import com.naigoapps.restaurantmobile.dto.RestaurantTableDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TablesRS {

    @GET("restaurant-tables")
    Call<List<RestaurantTableDTO>> list();

}

