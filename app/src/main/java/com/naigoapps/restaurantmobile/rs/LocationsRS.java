package com.naigoapps.restaurantmobile.rs;

import com.naigoapps.restaurantmobile.dto.LocationDTO;
import com.naigoapps.restaurantmobile.dto.MessageRequestDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface LocationsRS {

    @GET("locations")
    Call<LocationDTO[]> list();

    @POST("locations/message")
    Call<Void> message(@Body MessageRequestDTO dto);

}

