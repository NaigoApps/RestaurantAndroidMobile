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

public class OrdinationsAdapter extends RecyclerViewAdapter<OrdinationViewHolder, OrdinationDTO> {

    public OrdinationsAdapter(RecyclerView view) {
        super(view, "ordinations");
    }

    @NonNull
    @Override
    public OrdinationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OrdinationViewHolder(this, LayoutInflater.from(parent.getContext()).inflate(R.layout.ordination_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull OrdinationViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        holder.setOrdination(getData()[position]);
    }

    @Override
    protected boolean onItemActivated(ItemDetailsLookup.ItemDetails key) {
        return false;
    }
}
