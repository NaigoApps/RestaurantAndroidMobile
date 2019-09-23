package com.naigoapps.restaurantmobile.tables.table.ordinations;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.naigoapps.restaurantmobile.R;
import com.naigoapps.restaurantmobile.common.RecyclerViewAdapter;
import com.naigoapps.restaurantmobile.common.SelectionType;
import com.naigoapps.restaurantmobile.common.Utils;
import com.naigoapps.restaurantmobile.dto.OrdinationDTO;
import com.naigoapps.restaurantmobile.dto.PhaseOrdersDTO;
import com.naigoapps.restaurantmobile.tables.DiningTablesFragmentDirections;
import com.naigoapps.restaurantmobile.tables.table.DiningTableFragmentDirections;

public class OrdinationsAdapter extends RecyclerViewAdapter<OrdinationsAdapter.OrdinationViewHolder, OrdinationDTO> {

    public OrdinationsAdapter(Fragment fragment, RecyclerView view) {
        super(fragment, view, SelectionType.ONE);
    }

    @NonNull
    @Override
    public OrdinationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OrdinationViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.ordination_item, parent, false));
    }

    public class OrdinationViewHolder extends RecyclerViewAdapter<OrdinationViewHolder, OrdinationDTO>.IdentifiableViewHolder {

        private MaterialCardView cardView;

        private TextView ordinationTitleView;
        private OrdinationPhaseOrdersAdapter phasesListAdapter;

        public OrdinationViewHolder(View v) {
            super(v);
            cardView = v.findViewById(R.id.ordinationContainer);
            ordinationTitleView = v.findViewById(R.id.ordinationTitle);
            RecyclerView phasesList = v.findViewById(R.id.phasesList);
            phasesList.setLayoutManager(new LinearLayoutManager(phasesList.getContext()));
            phasesListAdapter = new OrdinationPhaseOrdersAdapter(phasesList);
            phasesList.setAdapter(phasesListAdapter);
        }

        @Override
        public void updateView(OrdinationDTO dto, boolean selected) {
            if (dto.isDirty()) {
//            cardView.setCardBackgroundColor(cardView.getResources().getColor(R.color.warning));
            }
            ordinationTitleView.setText(dto.format());
            phasesListAdapter.updateData(dto.getOrders().toArray(new PhaseOrdersDTO[0]));
            if (selected) {
                cardView.setCardElevation(4);
                cardView.setCardBackgroundColor(Utils.color(cardView.getContext(), R.color.colorAccent));
            }else{
                cardView.setCardElevation(1);
                cardView.setCardBackgroundColor(Utils.color(cardView.getContext(), R.color.colorTransparent));
            }
        }

        @Override
        public View getMainView() {
            return cardView;
        }

        @Override
        public View.OnClickListener getClickListener(OrdinationDTO dto) {
            return Navigation.createNavigateOnClickListener(R.id.editOrdination, DiningTableFragmentDirections
                    .editOrdination(dto.getDiningTableId(), dto.getUuid())
                    .getArguments());
        }
    }
}
