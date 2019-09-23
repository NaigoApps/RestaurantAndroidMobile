package com.naigoapps.restaurantmobile.rs;

import com.naigoapps.restaurantmobile.dto.CategoryDTO;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CategoriesRS {

    @GET("categories")
    Call<CategoryDTO[]> list();

}

