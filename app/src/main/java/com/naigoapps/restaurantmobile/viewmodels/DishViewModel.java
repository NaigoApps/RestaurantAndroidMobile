package com.naigoapps.restaurantmobile.viewmodels;

import androidx.core.util.Consumer;

import com.naigoapps.restaurantmobile.RemoteLoadTask;
import com.naigoapps.restaurantmobile.dto.DishDTO;
import com.naigoapps.restaurantmobile.tables.table.ordinations.editing.dishes.DishLoadTask;

public class DishViewModel extends RemoteDataViewModel<DishDTO> {

    private String dishUuid;

    public void setDishUuid(String dishUuid) {
        this.dishUuid = dishUuid;
    }

    @Override
    protected RemoteLoadTask<DishDTO> createTask(Consumer<DishDTO> consumer) {
        DishLoadTask dishLoadTask = new DishLoadTask(consumer);
        dishLoadTask.setDish(dishUuid);
        return dishLoadTask;
    }
}
