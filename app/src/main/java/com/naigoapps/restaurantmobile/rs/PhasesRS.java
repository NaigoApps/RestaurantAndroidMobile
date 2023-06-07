package com.naigoapps.restaurantmobile.rs;

import com.naigoapps.restaurantmobile.dto.PhaseDTO;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PhasesRS {

    @GET("phases")
    Call<PhaseDTO[]> list();

}

