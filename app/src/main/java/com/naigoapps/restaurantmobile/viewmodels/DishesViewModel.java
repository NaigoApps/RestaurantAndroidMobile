package com.naigoapps.restaurantmobile.viewmodels;

import androidx.core.util.Consumer;

import com.naigoapps.restaurantmobile.RemoteLoadTask;
import com.naigoapps.restaurantmobile.dto.DishDTO;
import com.naigoapps.restaurantmobile.tables.table.ordinations.editing.dishes.DishesLoadTask;

public class DishesViewModel extends RemoteDataViewModel<DishDTO[]> {

    private String categoryUuid;

    public void setCategoryUuid(String categoryUuid) {
        this.categoryUuid = categoryUuid;
    }

    @Override
    protected RemoteLoadTask<DishDTO[]> createTask(Consumer<DishDTO[]> consumer) {
        DishesLoadTask dishesLoadTask = new DishesLoadTask(consumer);
        dishesLoadTask.setCategory(categoryUuid);
        return dishesLoadTask;
    }
}
