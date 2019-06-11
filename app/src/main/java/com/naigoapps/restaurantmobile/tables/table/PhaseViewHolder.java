package com.naigoapps.restaurantmobile.tables.table;

import android.view.View;
import android.widget.TextView;

import com.naigoapps.restaurantmobile.R;
import com.naigoapps.restaurantmobile.dto.OrdersGroupDTO;
import com.naigoapps.restaurantmobile.dto.PhaseDTO;
import com.naigoapps.restaurantmobile.tables.IdentifiableViewHolder;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class PhaseViewHolder extends IdentifiableViewHolder {

    private TextView phaseTitleView;
    private RecyclerView ordersListView;

    public PhaseViewHolder(PhasesAdapter adapter, View v) {
        super(adapter, v);
        phaseTitleView = v.findViewById(R.id.phaseTitle);
        ordersListView = v.findViewById(R.id.ordersList);
        ordersListView.setLayoutManager(new LinearLayoutManager(ordersListView.getContext()));
    }

    public void setPhase(PhaseDTO phase){
        phaseTitleView.setText(phase.getName());
    }

    public void setOrders(List<OrdersGroupDTO> orders){
        OrderGroupsAdapter adapter = new OrderGroupsAdapter(ordersListView);
        adapter.updateData(orders.stream().toArray(OrdersGroupDTO[]::new));
        ordersListView.setAdapter(adapter);
    }
}