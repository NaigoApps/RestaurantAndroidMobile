package com.naigoapps.restaurantmobile.rs;

import com.naigoapps.restaurantmobile.dto.WaiterDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface WaitersRS {

    @GET("waiters/active")
    Call<List<WaiterDTO>> list();

}

