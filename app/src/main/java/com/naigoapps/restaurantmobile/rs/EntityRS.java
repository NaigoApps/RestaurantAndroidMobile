package com.naigoapps.restaurantmobile.rs;

import com.naigoapps.restaurantmobile.dto.Identifiable;

import retrofit2.Call;
import retrofit2.http.GET;

public interface EntityRS<I extends Identifiable> {

    @GET
    Call<I[]> list();

}

