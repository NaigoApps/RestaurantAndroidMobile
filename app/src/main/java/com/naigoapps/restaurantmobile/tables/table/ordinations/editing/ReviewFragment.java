package com.naigoapps.restaurantmobile.tables.table.ordinations.editing;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.naigoapps.restaurantmobile.R;
import com.naigoapps.restaurantmobile.dto.PhaseOrdersDTO;
import com.naigoapps.restaurantmobile.viewmodels.OrdinationEditorViewModel;

public class ReviewFragment extends Fragment {

    private OrdinationEditorViewModel viewModel;

    public ReviewFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_review, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = ViewModelProviders.of(getActivity()).get(OrdinationEditorViewModel.class);

        RecyclerView phasesView = view.findViewById(R.id.phasesContainer);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(RecyclerView.VERTICAL);
        phasesView.setLayoutManager(manager);
        EditablePhaseGroupAdapter adapter = new EditablePhaseGroupAdapter(this, phasesView);
        phasesView.setAdapter(adapter);
        viewModel.getCurrentOrdination().observe(this, ordination -> {
            adapter.updateData(ordination.getOrders().toArray(new PhaseOrdersDTO[0]));
        });
    }

}
