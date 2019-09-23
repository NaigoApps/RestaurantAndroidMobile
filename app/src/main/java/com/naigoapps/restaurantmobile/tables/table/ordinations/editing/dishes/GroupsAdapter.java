package com.naigoapps.restaurantmobile.tables.table.ordinations.editing.dishes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.naigoapps.restaurantmobile.R;
import com.naigoapps.restaurantmobile.common.RecyclerViewAdapter;
import com.naigoapps.restaurantmobile.common.SelectionType;
import com.naigoapps.restaurantmobile.dto.OrdersGroupDTO;
import com.naigoapps.restaurantmobile.viewmodels.OrdinationEditorViewModel;

public class GroupsAdapter extends RecyclerViewAdapter<GroupsAdapter.GroupViewHolder, OrdersGroupDTO> {

    private OrdinationEditorViewModel viewModel;

    public GroupsAdapter(Fragment fragment, RecyclerView view, OrdinationEditorViewModel viewModel) {
        super(fragment, view, SelectionType.NONE);
        this.viewModel = viewModel;
        viewModel.getCurrentOrdination().observe(fragment, (value) -> refreshViewHolders());
    }

    @NonNull
    @Override
    public GroupsAdapter.GroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new GroupViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.group_item, parent, false));
    }

    public class GroupViewHolder extends RecyclerViewAdapter<GroupViewHolder, OrdersGroupDTO>.IdentifiableViewHolder{

        private TextView label;
        private ImageView btnLess;
        private ImageView btnMore;

        public GroupViewHolder(@NonNull View itemView) {
            super(itemView);
            label = itemView.findViewById(R.id.groupNameView);
            btnLess = itemView.findViewById(R.id.btnLess);
            btnMore = itemView.findViewById(R.id.btnMore);
        }

        @Override
        public View getMainView() {
            return null;
        }

        @Override
        public void updateView(OrdersGroupDTO dto, boolean selected) {
            label.setText(OrdinationEditorViewModel.formatGroup(dto, true, false));
            btnLess.setOnClickListener(evt -> viewModel.reduceGroup(dto));
            btnMore.setOnClickListener(evt -> viewModel.increaseGroup(dto));
        }

        @Override
        public View.OnClickListener getClickListener(OrdersGroupDTO dto) {
            return null;
        }
    }
}
