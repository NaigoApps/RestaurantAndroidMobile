package com.naigoapps.restaurantmobile.rs;

import com.naigoapps.restaurantmobile.dto.AdditionDTO;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AdditionsRS {

    @GET("additions")
    Call<AdditionDTO[]> list(@Query("dish") String dish);

}

