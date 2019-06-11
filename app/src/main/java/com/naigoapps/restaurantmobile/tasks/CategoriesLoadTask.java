package com.naigoapps.restaurantmobile.tasks;

import com.naigoapps.restaurantmobile.RemoteLoadTask;
import com.naigoapps.restaurantmobile.dto.CategoryDTO;
import com.naigoapps.restaurantmobile.rs.CategoriesRS;
import com.naigoapps.restaurantmobile.rs.RSFactory;

import java.io.IOException;
import java.util.List;

import androidx.core.util.Consumer;
import androidx.fragment.app.FragmentActivity;
import retrofit2.Response;

public class CategoriesLoadTask extends RemoteLoadTask<CategoryDTO[]> {

    public CategoriesLoadTask(FragmentActivity activity, Consumer<CategoryDTO[]> consumer) {
        super(activity, consumer);
    }

    @Override
    protected Response<CategoryDTO[]> makeRequest(FragmentActivity activity) throws IOException{
        CategoriesRS catRS = RSFactory.createService(activity, CategoriesRS.class);
        return catRS.list().execute();
    }
}