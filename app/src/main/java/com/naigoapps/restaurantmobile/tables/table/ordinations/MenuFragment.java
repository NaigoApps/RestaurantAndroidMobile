package com.naigoapps.restaurantmobile.tables.table.ordinations;

import android.os.Bundle;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.naigoapps.restaurantmobile.R;
import com.naigoapps.restaurantmobile.dto.CategoryDTO;
import com.naigoapps.restaurantmobile.tables.RecyclerViewAdapter;
import com.naigoapps.restaurantmobile.tables.RemoteRecyclerViewFragment;
import com.naigoapps.restaurantmobile.viewmodels.CategoriesViewModel;
import com.naigoapps.restaurantmobile.viewmodels.RemoteViewModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

public class MenuFragment extends RemoteRecyclerViewFragment<CategoryDTO> {

    public MenuFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.menu_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    protected RemoteViewModel<CategoryDTO[]> getViewModel() {
        return ViewModelProviders.of(getActivity()).get(CategoriesViewModel.class);
    }

    @Override
    protected RecyclerViewAdapter<?, CategoryDTO> createAdapter(RecyclerView view) {
        return new CategoriesAdapter(view);
    }

    @Override
    protected ActionMode.Callback createActionMode() {
        return null;
    }
}
