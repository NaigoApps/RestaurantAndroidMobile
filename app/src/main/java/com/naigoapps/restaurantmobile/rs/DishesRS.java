package com.naigoapps.restaurantmobile.rs;

import com.naigoapps.restaurantmobile.dto.DishDTO;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface DishesRS {

    @GET("dishes")
    Call<DishDTO[]> list(@Query("category") String categoryUuid);

    @GET("dishes/{uuid}")
    Call<DishDTO> get(@Path("uuid") String dishUuid);
}

