package com.naigoapps.restaurantmobile.viewmodels;

import androidx.core.util.Consumer;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.naigoapps.restaurantmobile.RemoteLoadTask;
import com.naigoapps.restaurantmobile.dto.AdditionDTO;
import com.naigoapps.restaurantmobile.tasks.AdditionsLoadTask;

public class AdditionsViewModel extends RemoteDataViewModel<AdditionDTO[]> {

    private String dish;

    public void setDish(String dish) {
        this.dish = dish;
    }

    @Override
    protected RemoteLoadTask<AdditionDTO[]> createTask(Consumer<AdditionDTO[]> consumer) {
        return new AdditionsLoadTask(consumer, dish);
    }

    public static AdditionsViewModel get(Fragment f){
        return ViewModelProviders.of(f).get(AdditionsViewModel.class);
    }
}
