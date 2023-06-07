package com.naigoapps.restaurantmobile.tables.table.ordinations.editing;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.naigoapps.restaurantmobile.R;
import com.naigoapps.restaurantmobile.common.RecyclerViewAdapter;
import com.naigoapps.restaurantmobile.common.RemoteRecyclerViewAdapter;
import com.naigoapps.restaurantmobile.common.SelectionType;
import com.naigoapps.restaurantmobile.dto.PhaseDTO;
import com.naigoapps.restaurantmobile.viewmodels.OrdinationEditorViewModel;
import com.naigoapps.restaurantmobile.viewmodels.PhasesViewModel;

public class PhasesAdapter extends RemoteRecyclerViewAdapter<PhasesAdapter.PhaseViewHolder, PhasesViewModel, PhaseDTO> {

    private OrdinationEditorViewModel ordinationViewModel;

    public PhasesAdapter(Fragment fragment, RecyclerView view, OrdinationEditorViewModel viewModel) {
        super(fragment, view, SelectionType.FORCE_ONE, phasesViewModel -> {
        });
        this.ordinationViewModel = viewModel;
        observeSelection(fragment, phases -> {
            if(!phases.isEmpty()) {
                viewModel.setSelectedPhase(phases.iterator().next());
            }
        });
    }

    @Override
    public void updateData(PhaseDTO[] phaseDTOS) {
        super.updateData(phaseDTOS);
        if(ordinationViewModel.getSelectedPhase() != null){
            select(ordinationViewModel.getSelectedPhase().getUuid());
        }else if(phaseDTOS.length > 0){
            select(phaseDTOS[0].getUuid());
        }
    }

    @NonNull
    @Override
    public PhaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PhaseViewHolder(this, LayoutInflater.from(parent.getContext()).inflate(R.layout.phase_item, parent, false));
    }

    @Override
    public PhasesViewModel createViewModel(ViewModelProvider provider) {
        return provider.get(PhasesViewModel.class);
    }


    public class PhaseViewHolder extends RecyclerViewAdapter<PhaseViewHolder, PhaseDTO>.IdentifiableViewHolder {

        private TextView phaseNameView;

        public PhaseViewHolder(PhasesAdapter adapter, View v) {
            super(v);
            phaseNameView = v.findViewById(R.id.phaseNameView);
        }

        @Override
        public View.OnClickListener getClickListener(PhaseDTO dto) {
            return v -> setSelection(dto.getUuid());
        }

        @Override
        public View getMainView() {
            return phaseNameView;
        }

        @Override
        public void updateView(PhaseDTO dto, boolean selected) {
            phaseNameView.setText(dto.getName());
            phaseNameView.setActivated(selected);
        }

    }
}
