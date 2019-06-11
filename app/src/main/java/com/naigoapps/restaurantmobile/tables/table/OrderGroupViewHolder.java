package com.naigoapps.restaurantmobile.tables.table;

import android.view.View;
import android.widget.TextView;

import com.naigoapps.restaurantmobile.R;
import com.naigoapps.restaurantmobile.dto.OrdersGroupDTO;
import com.naigoapps.restaurantmobile.tables.IdentifiableViewHolder;

public class OrderGroupViewHolder extends IdentifiableViewHolder {

    private TextView orderGroupView;

    public OrderGroupViewHolder(OrderGroupsAdapter adapter, View v) {
        super(adapter, v);
        orderGroupView = v.findViewById(R.id.orderGroupView);
    }

    public void setOrder(OrdersGroupDTO order){
        orderGroupView.setText(order.getDish().getName());
    }

}