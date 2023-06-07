package com.naigoapps.restaurantmobile.tables.table.ordinations.editing.categories;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.naigoapps.restaurantmobile.R;
import com.naigoapps.restaurantmobile.dto.OrdinationDTO;
import com.naigoapps.restaurantmobile.viewmodels.OrdinationEditorViewModel;

public class CategoriesMenuFragment extends Fragment {

    public CategoriesMenuFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_menu_categories, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        CategoriesMenuFragmentArgs args = CategoriesMenuFragmentArgs.fromBundle(getArguments());
        OrdinationEditorViewModel viewModel = ViewModelProviders.of(getActivity()).get(OrdinationEditorViewModel.class);

        OrdinationDTO last = viewModel.getCurrentOrdination().getValue();
        if(last == null || !last.getUuid().equals(args.getOrdinationUuid())) {
            viewModel.loadOrdination(args.getTableUuid(), args.getOrdinationUuid());
        }
    }
}
