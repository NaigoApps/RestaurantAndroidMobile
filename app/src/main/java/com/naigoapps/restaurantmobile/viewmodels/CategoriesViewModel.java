package com.naigoapps.restaurantmobile.viewmodels;

import com.naigoapps.restaurantmobile.RemoteLoadTask;
import com.naigoapps.restaurantmobile.dto.CategoryDTO;
import com.naigoapps.restaurantmobile.dto.RestaurantTableDTO;
import com.naigoapps.restaurantmobile.tasks.CategoriesLoadTask;
import com.naigoapps.restaurantmobile.tasks.RestaurantTablesLoadTask;

import java.util.List;

import androidx.core.util.Consumer;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;

public class CategoriesViewModel extends RemoteViewModel<CategoryDTO[]> {

    @Override
    protected RemoteLoadTask<CategoryDTO[]> createTask(FragmentActivity owner, Consumer<CategoryDTO[]> consumer) {
        return new CategoriesLoadTask(owner, consumer);
    }

    public static CategoriesViewModel get(FragmentActivity f){
        return ViewModelProviders.of(f).get(CategoriesViewModel.class);
    }
}
