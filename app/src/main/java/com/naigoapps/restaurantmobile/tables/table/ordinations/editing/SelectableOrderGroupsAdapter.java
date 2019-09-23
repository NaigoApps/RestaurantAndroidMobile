package com.naigoapps.restaurantmobile.tables.table.ordinations.editing;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.naigoapps.restaurantmobile.R;
import com.naigoapps.restaurantmobile.common.RecyclerViewAdapter;
import com.naigoapps.restaurantmobile.common.SelectionType;
import com.naigoapps.restaurantmobile.dto.AdditionDTO;
import com.naigoapps.restaurantmobile.dto.OrdersGroupDTO;

import java.util.ArrayList;
import java.util.List;

public class SelectableOrderGroupsAdapter extends RecyclerViewAdapter<SelectableOrderGroupsAdapter.SelectableOrderGroupViewHolder, OrdersGroupDTO> {

    public SelectableOrderGroupsAdapter(RecyclerView view) {
        this(null, view);
    }

    public SelectableOrderGroupsAdapter(Fragment fragment, RecyclerView view) {
        super(fragment, view, SelectionType.NONE);
    }

    @NonNull
    @Override
    public SelectableOrderGroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SelectableOrderGroupViewHolder(this, LayoutInflater.from(parent.getContext()).inflate(R.layout.selectable_order_group_item, parent, false));
    }

    public class SelectableOrderGroupViewHolder extends RecyclerViewAdapter<SelectableOrderGroupViewHolder, OrdersGroupDTO>.IdentifiableViewHolder {

        private MaterialButton orderGroupView;

        public SelectableOrderGroupViewHolder(SelectableOrderGroupsAdapter adapter, View v) {
            super(v);
            orderGroupView = v.findViewById(R.id.orderGroupView);
        }

        private String formatGroup(OrdersGroupDTO group) {
            List<String> parts = new ArrayList<>();
            parts.add(String.valueOf(group.getQuantity()));
            parts.add(group.getDish().getName());
            for (AdditionDTO addition : group.getAdditions()) {
                parts.add(addition.getName());
            }
            if (group.getNotes() != null) {
                parts.add(group.getNotes());
            }
            return TextUtils.join(" ", parts);
        }

        @Override
        public View.OnClickListener getClickListener(OrdersGroupDTO dto) {
            return null;
        }

        @Override
        public View getMainView() {
            return orderGroupView;
        }

        @Override
        public void updateView(OrdersGroupDTO dto, boolean selected) {
            orderGroupView.setText(formatGroup(dto));
        }
    }
}
