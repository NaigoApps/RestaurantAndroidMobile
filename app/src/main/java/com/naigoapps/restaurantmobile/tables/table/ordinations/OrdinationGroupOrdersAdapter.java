package com.naigoapps.restaurantmobile.tables.table.ordinations;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.naigoapps.restaurantmobile.R;
import com.naigoapps.restaurantmobile.common.RecyclerViewAdapter;
import com.naigoapps.restaurantmobile.common.SelectionType;
import com.naigoapps.restaurantmobile.dto.AdditionDTO;
import com.naigoapps.restaurantmobile.dto.OrdersGroupDTO;
import com.naigoapps.restaurantmobile.viewmodels.OrdinationEditorViewModel;

import java.util.ArrayList;
import java.util.List;

public class OrdinationGroupOrdersAdapter extends RecyclerViewAdapter<OrdinationGroupOrdersAdapter.OrderGroupViewHolder, OrdersGroupDTO> {

    public OrdinationGroupOrdersAdapter(RecyclerView view) {
        this(null, view);
    }

    public OrdinationGroupOrdersAdapter(Fragment fragment, RecyclerView view) {
        super(fragment, view, SelectionType.NONE, false);
    }

    @NonNull
    @Override
    public OrderGroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OrderGroupViewHolder(this, LayoutInflater.from(parent.getContext()).inflate(R.layout.order_group_item, parent, false));
    }

    public class OrderGroupViewHolder extends RecyclerViewAdapter<OrderGroupViewHolder, OrdersGroupDTO>.IdentifiableViewHolder {

        private TextView orderGroupView;

        public OrderGroupViewHolder(OrdinationGroupOrdersAdapter adapter, View v) {
            super(v);
            orderGroupView = v.findViewById(R.id.orderGroupView);
        }

        @Override
        public void updateView(OrdersGroupDTO dto, boolean selected) {
            orderGroupView.setText(OrdinationEditorViewModel.formatGroup(dto));
        }

        @Override
        public View getMainView() {
            return null;
        }

        @Override
        public View.OnClickListener getClickListener(OrdersGroupDTO dto) {
            return null;
        }
    }
}
