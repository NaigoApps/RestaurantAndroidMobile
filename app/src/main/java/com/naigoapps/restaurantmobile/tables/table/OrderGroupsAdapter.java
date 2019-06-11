package com.naigoapps.restaurantmobile.tables.table;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.naigoapps.restaurantmobile.R;
import com.naigoapps.restaurantmobile.dto.OrdersGroupDTO;
import com.naigoapps.restaurantmobile.tables.RecyclerViewAdapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.selection.ItemDetailsLookup;
import androidx.recyclerview.widget.RecyclerView;

public class OrderGroupsAdapter extends RecyclerViewAdapter<OrderGroupViewHolder, OrdersGroupDTO> {

    public OrderGroupsAdapter(RecyclerView view) {
        super(view, "orders");
    }

    @NonNull
    @Override
    public OrderGroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OrderGroupViewHolder(this, LayoutInflater.from(parent.getContext()).inflate(R.layout.order_group_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull OrderGroupViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        holder.setOrder(getData()[position]);
    }

    @Override
    protected boolean onItemActivated(ItemDetailsLookup.ItemDetails key) {
        return false;
    }
}
