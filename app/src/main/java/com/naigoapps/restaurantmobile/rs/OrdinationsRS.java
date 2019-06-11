package com.naigoapps.restaurantmobile.rs;

import com.naigoapps.restaurantmobile.dto.OrdinationDTO;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface OrdinationsRS {

    @GET("dining-tables/{tableUuid}/ordinations")
    public Call<OrdinationDTO[]> list(@Path("tableUuid") String tableUuid);

}

