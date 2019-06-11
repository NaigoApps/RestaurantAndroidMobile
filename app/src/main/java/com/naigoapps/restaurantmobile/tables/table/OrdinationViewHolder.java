package com.naigoapps.restaurantmobile.tables.table;

import android.view.View;
import android.widget.TextView;

import com.google.android.material.card.MaterialCardView;
import com.naigoapps.restaurantmobile.R;
import com.naigoapps.restaurantmobile.dto.OrdinationDTO;
import com.naigoapps.restaurantmobile.dto.PhaseOrdersDTO;
import com.naigoapps.restaurantmobile.tables.IdentifiableViewHolder;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class OrdinationViewHolder extends IdentifiableViewHolder {

    private MaterialCardView cardView;

    private TextView ordinationTitleView;
    private RecyclerView phasesList;

    public OrdinationViewHolder(OrdinationsAdapter adapter, View v) {
        super(adapter, v);
        cardView = v.findViewById(R.id.ordinationContainer);
        ordinationTitleView = v.findViewById(R.id.ordinationTitle);
        phasesList = v.findViewById(R.id.phasesList);
        phasesList.setLayoutManager(new LinearLayoutManager(phasesList.getContext()));
    }

    public void setOrdination(OrdinationDTO ordination){
        if(ordination.isDirty()){
//            cardView.setCardBackgroundColor(cardView.getResources().getColor(R.color.warning));
        }

        ordinationTitleView.setText(ordination.format());

        PhasesAdapter adapter = new PhasesAdapter(phasesList);
        adapter.updateData(ordination.getOrders().toArray(new PhaseOrdersDTO[0]));
        phasesList.setAdapter(adapter);
    }

}