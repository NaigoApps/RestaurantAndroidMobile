package com.naigoapps.restaurantmobile.tables.table.ordinations.editing;

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

public class EditablePhaseGroupAdapter extends RecyclerViewAdapter<EditablePhaseGroupAdapter.EditablePhaseGroupViewHolder, PhaseOrdersDTO> {

    public EditablePhaseGroupAdapter(Fragment fragment, RecyclerView view) {
        super(fragment, view, SelectionType.NONE);
    }

    @NonNull
    @Override
    public EditablePhaseGroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new EditablePhaseGroupViewHolder(this, LayoutInflater.from(parent.getContext()).inflate(R.layout.editable_phase_groups_item, parent, false));
    }

    public class EditablePhaseGroupViewHolder extends RecyclerViewAdapter<EditablePhaseGroupViewHolder, PhaseOrdersDTO>.IdentifiableViewHolder {

        private TextView phaseNameView;
        private RecyclerView groupsList;
        private SelectableOrderGroupsAdapter groupsListAdapter;

        public EditablePhaseGroupViewHolder(EditablePhaseGroupAdapter adapter, View v) {
            super(v);
            phaseNameView = v.findViewById(R.id.phaseNameView);
            groupsList = v.findViewById(R.id.groupList);
            groupsList.setLayoutManager(new LinearLayoutManager(groupsList.getContext()));
            groupsListAdapter = new SelectableOrderGroupsAdapter(groupsList);
            groupsList.setAdapter(groupsListAdapter);
        }

        @Override
        public View getMainView() {
            return null;
        }

        @Override
        public View.OnClickListener getClickListener(PhaseOrdersDTO dto) {
            return null;
        }

        @Override
        public void updateView(PhaseOrdersDTO dto, boolean selected) {
            phaseNameView.setText(dto.getPhase().getName());
            groupsListAdapter.updateData(dto.getOrders().toArray(new OrdersGroupDTO[0]));
        }

    }

}
