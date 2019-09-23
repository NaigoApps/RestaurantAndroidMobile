package com.naigoapps.restaurantmobile.tables.table.ordinations;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.naigoapps.restaurantmobile.R;
import com.naigoapps.restaurantmobile.common.RecyclerViewAdapter;
import com.naigoapps.restaurantmobile.common.SelectionType;
import com.naigoapps.restaurantmobile.dto.OrdersGroupDTO;
import com.naigoapps.restaurantmobile.dto.PhaseOrdersDTO;

public class OrdinationPhaseOrdersAdapter extends RecyclerViewAdapter<OrdinationPhaseOrdersAdapter.OrdinationPhaseOrdersViewHolder, PhaseOrdersDTO> {

    public OrdinationPhaseOrdersAdapter(RecyclerView view) {
        this(null, view);
    }

    public OrdinationPhaseOrdersAdapter(Fragment fragment, RecyclerView view) {
        super(fragment, view, SelectionType.NONE);
    }

    @NonNull
    @Override
    public OrdinationPhaseOrdersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OrdinationPhaseOrdersViewHolder(this, LayoutInflater.from(parent.getContext()).inflate(R.layout.phase_groups_item, parent, false));
    }

    public class OrdinationPhaseOrdersViewHolder extends RecyclerViewAdapter<OrdinationPhaseOrdersViewHolder, PhaseOrdersDTO>.IdentifiableViewHolder {

        private TextView phaseTitleView;
        private RecyclerView ordersListView;

        public OrdinationPhaseOrdersViewHolder(OrdinationPhaseOrdersAdapter adapter, View v) {
            super(v);
            phaseTitleView = v.findViewById(R.id.phaseTitle);
            ordersListView = v.findViewById(R.id.ordersList);
            ordersListView.setLayoutManager(new LinearLayoutManager(ordersListView.getContext()));
        }

        @Override
        public void updateView(PhaseOrdersDTO dto, boolean selected) {
            phaseTitleView.setText(dto.getPhase().getName());
            OrdinationGroupOrdersAdapter adapter = new OrdinationGroupOrdersAdapter(ordersListView);
            adapter.updateData(dto.getOrders().toArray(new OrdersGroupDTO[0]));
            ordersListView.setAdapter(adapter);
        }

        @Override
        public View.OnClickListener getClickListener(PhaseOrdersDTO dto) {
            return null;
        }

        @Override
        public View getMainView() {
            return null;
        }

    }
}
