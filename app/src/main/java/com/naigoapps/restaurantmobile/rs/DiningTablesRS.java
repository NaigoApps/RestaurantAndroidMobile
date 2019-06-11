package com.naigoapps.restaurantmobile.rs;

import com.naigoapps.restaurantmobile.dto.DiningTableDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface DiningTablesRS {

    @GET("dining-tables")
    public Call<DiningTableDTO[]> list();

    @GET("dining-tables/{uuid}")
    public Call<DiningTableDTO> load(@Path("uuid") String uuid);

    @POST("dining-tables")
    public Call<DiningTableDTO> create(@Body DiningTableDTO dto);

    @PUT("dining-tables/{uuid}")
    public Call<DiningTableDTO> edit(@Path("uuid") String uuid, @Body DiningTableDTO dto);

    @DELETE("dining-tables/{uuid}")
    public Call<Boolean> delete(@Path("uuid") String uuid);
}

