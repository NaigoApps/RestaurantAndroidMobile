package com.naigoapps.restaurantmobile.tables.table;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.naigoapps.restaurantmobile.R;
import com.naigoapps.restaurantmobile.dto.OrdinationDTO;
import com.naigoapps.restaurantmobile.dto.PhaseOrdersDTO;
import com.naigoapps.restaurantmobile.tables.RecyclerViewAdapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.selection.ItemDetailsLookup;
import androidx.recyclerview.widget.RecyclerView;

public class PhasesAdapter extends RecyclerViewAdapter<PhaseViewHolder, PhaseOrdersDTO> {

    public PhasesAdapter(RecyclerView view) {
        super(view, "ordinations");
    }

    @NonNull
    @Override
    public PhaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PhaseViewHolder(this, LayoutInflater.from(parent.getContext()).inflate(R.layout.phase_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PhaseViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);

        PhaseOrdersDTO ordination = getData()[position];

        holder.setPhase(ordination.getPhase());
        holder.setOrders(ordination.getOrders());
    }

    @Override
    protected boolean onItemActivated(ItemDetailsLookup.ItemDetails key) {
        return false;
    }
}
