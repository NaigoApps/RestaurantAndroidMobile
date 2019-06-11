package com.naigoapps.restaurantmobile.tables;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.naigoapps.restaurantmobile.R;
import com.naigoapps.restaurantmobile.dto.DiningTableDTO;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.selection.ItemDetailsLookup;
import androidx.recyclerview.widget.RecyclerView;

public class DiningTablesAdapter extends RecyclerViewAdapter<DiningTableViewHolder, DiningTableDTO> {

    public DiningTablesAdapter(RecyclerView view) {
        super(view, "dining-tables");
    }

    @NonNull
    @Override
    public DiningTableViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DiningTableViewHolder(this, LayoutInflater.from(parent.getContext()).inflate(R.layout.dining_table_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DiningTableViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);

        DiningTableDTO table = getData()[position];

        holder.setDiningTable(table);
    }

    @Override
    protected boolean onItemActivated(ItemDetailsLookup.ItemDetails key) {
        Bundle bundle = new Bundle();
        DiningTableDTO dto = getData()[key.getPosition()];
        bundle.putString("uuid", dto.getUuid());
        Navigation.findNavController(view).navigate(R.id.selectTable, bundle);
        return true;
    }
}
