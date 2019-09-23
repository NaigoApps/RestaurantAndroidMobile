package com.naigoapps.restaurantmobile.tables.table.ordinations.editing;


import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.naigoapps.restaurantmobile.R;
import com.naigoapps.restaurantmobile.common.RecyclerViewAdapter;
import com.naigoapps.restaurantmobile.common.RemoteCrudListFragment;
import com.naigoapps.restaurantmobile.dto.PhaseDTO;
import com.naigoapps.restaurantmobile.viewmodels.OrdinationEditorViewModel;
import com.naigoapps.restaurantmobile.viewmodels.PhasesViewModel;
import com.naigoapps.restaurantmobile.viewmodels.RemoteDataViewModel;

public class PhasesFragment extends RemoteCrudListFragment<PhaseDTO> {

    private OrdinationEditorViewModel ordinationViewModel;

    public PhasesFragment() {
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
        RecyclerView list = view.findViewById(R.id.list);
        list.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
    }

    @Override
    protected RemoteDataViewModel<PhaseDTO[]> createViewModel() {
        return ViewModelProviders.of(this).get(PhasesViewModel.class);
    }

    @Override
    protected RecyclerViewAdapter<?, PhaseDTO> createAdapter(RecyclerView view) {
        return new PhasesAdapter(this, view, ordinationViewModel);
    }

    @Override
    public void onAdd() {
        //Cannot add a phase
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
