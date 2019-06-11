package com.naigoapps.restaurantmobile.rs;

import com.naigoapps.restaurantmobile.dto.EveningDTO;

import retrofit2.Call;
import retrofit2.http.GET;

public interface EveningRS {

    @GET("evenings/selected")
    public Call<EveningDTO> selected();

}

