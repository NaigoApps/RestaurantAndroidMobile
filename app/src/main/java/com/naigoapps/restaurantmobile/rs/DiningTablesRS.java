package com.naigoapps.restaurantmobile.rs;

import com.naigoapps.restaurantmobile.dto.DiningTableDTO;
import com.naigoapps.restaurantmobile.dto.DiningTableSkeletonDTO;
import com.naigoapps.restaurantmobile.dto.WrapperDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface DiningTablesRS {

    @GET("dining-tables/open")
    Call<DiningTableDTO[]> list();

    @GET("dining-tables/{uuid}")
    Call<DiningTableDTO> load(@Path("uuid") String uuid);

    @GET("dining-tables/{uuid}/skeleton")
    Call<DiningTableSkeletonDTO> loadSkeleton(@Path("uuid") String uuid);

    @POST("dining-tables")
    Call<DiningTableSkeletonDTO> create(@Body DiningTableSkeletonDTO dto);

    @PUT("dining-tables/{uuid}")
    Call<DiningTableSkeletonDTO> edit(@Path("uuid") String uuid, @Body DiningTableSkeletonDTO dto);

    @DELETE("dining-tables/{uuid}")
    Call<Boolean> delete(@Path("uuid") String uuid);
}

