package com.naigoapps.restaurantmobile.rs;

import com.naigoapps.restaurantmobile.dto.OrdinationDTO;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface OrdinationsRS {

    @GET("dining-tables/{tableUuid}/ordinations")
    Call<OrdinationDTO[]> list(@Path("tableUuid") String tableUuid);

    @GET("dining-tables/{tableUuid}/ordinations/{ordinationUuid}")
    Call<OrdinationDTO> get(@Path("tableUuid") String tableUuid, @Path("ordinationUuid") String ordinationUuid);

    @POST("dining-tables/{tableUuid}/ordinations/skeleton")
    Call<OrdinationDTO> create(@Path("tableUuid") String tableUuid);

    @DELETE("dining-tables/{tableUuid}/ordinations/{ordinationUuid}")
    Call<Boolean> delete(@Path("tableUuid") String tableUuid, @Path("ordinationUuid") String ordinationUuid);

    @PUT("dining-tables/{tableUuid}/ordinations/{ordinationUuid}")
    Call<OrdinationDTO> edit(@Path("tableUuid") String tableUuid, @Path("ordinationUuid") String ordinationUuid, @Body OrdinationDTO content);

    @POST("dining-tables/{tableUuid}/ordinations/{ordinationUuid}/print")
    Call<Void> print(@Path("tableUuid") String tableUuid, @Path("ordinationUuid") String ordinationUuid);
}

