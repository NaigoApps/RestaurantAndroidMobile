package com.naigoapps.restaurantmobile.tables.table.ordinations.editing;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.naigoapps.restaurantmobile.R;
import com.naigoapps.restaurantmobile.viewmodels.OrdinationEditorViewModel;

public class MenuFragment extends Fragment {

    private OrdinationEditorViewModel viewModel;

    public MenuFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_menu_categories, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getActivity() != null) {
            viewModel = ViewModelProviders.of(getActivity()).get(OrdinationEditorViewModel.class);

//            Fragment navHostFragment = getActivity().getSupportFragmentManager().findFragmentById(R.id.menu_host_fragment);
//            NavController navController = NavHostFragment.findNavController(navHostFragment);
        }
    }
}
