package com.naigoapps.restaurantmobile.viewmodels;

import androidx.core.util.Consumer;
import androidx.lifecycle.ViewModelProviders;

import com.naigoapps.restaurantmobile.Application;
import com.naigoapps.restaurantmobile.RemoteLoadTask;
import com.naigoapps.restaurantmobile.dto.CategoryDTO;
import com.naigoapps.restaurantmobile.tables.table.ordinations.editing.categories.CategoriesLoadTask;

public class CategoriesViewModel extends RemoteDataViewModel<CategoryDTO[]> {

    @Override
    protected RemoteLoadTask<CategoryDTO[]> createTask(Consumer<CategoryDTO[]> consumer) {
        return new CategoriesLoadTask(consumer);
    }

    public static CategoriesViewModel get(){
        return ViewModelProviders.of(Application.getActivity()).get(CategoriesViewModel.class);
    }
}
