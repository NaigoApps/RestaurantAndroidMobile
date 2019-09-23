package com.naigoapps.restaurantmobile.tables.table.ordinations.editing.dishes;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.naigoapps.restaurantmobile.R;
import com.naigoapps.restaurantmobile.common.RecyclerViewAdapter;
import com.naigoapps.restaurantmobile.common.RemoteCrudListFragment;
import com.naigoapps.restaurantmobile.dto.DishDTO;
import com.naigoapps.restaurantmobile.viewmodels.DishesViewModel;
import com.naigoapps.restaurantmobile.viewmodels.OrdinationEditorViewModel;
import com.naigoapps.restaurantmobile.viewmodels.RemoteDataViewModel;

public class DishesFragment extends RemoteCrudListFragment<DishDTO> {

    private OrdinationEditorViewModel ordinationViewModel;

    public DishesFragment() {
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        ordinationViewModel = ViewModelProviders.of(getActivity()).get(OrdinationEditorViewModel.class);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.btnAdd).setVisibility(View.GONE);
    }

    @Override
    protected RemoteDataViewModel<DishDTO[]> createViewModel() {
        DishesViewModel viewModel = ViewModelProviders.of(this).get(DishesViewModel.class);
        String category = ordinationViewModel.getSelectedCategory().getUuid();
        viewModel.setCategoryUuid(category);
        return viewModel;
    }

    @Override
    protected RecyclerViewAdapter<?, DishDTO> createAdapter(RecyclerView view) {
        return new DishesAdapter(this, view, ordinationViewModel);
    }

    @Override
    public void onAdd() {
        //Cannot add a dish
    }

    @Override
    public Integer getContextualMenuId() {
        return null;
    }

    @Override
    public boolean onContextualMenuClick(int menuItemId) {
        return false;
    }

}
