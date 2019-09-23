package com.naigoapps.restaurantmobile.tables.table.ordinations.editing.dishes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.naigoapps.restaurantmobile.R;
import com.naigoapps.restaurantmobile.common.RecyclerViewAdapter;
import com.naigoapps.restaurantmobile.common.SelectionType;
import com.naigoapps.restaurantmobile.dto.DishDTO;
import com.naigoapps.restaurantmobile.dto.OrdersGroupDTO;
import com.naigoapps.restaurantmobile.viewmodels.OrdinationEditorViewModel;

public class DishesAdapter extends RecyclerViewAdapter<DishesAdapter.DishViewHolder, DishDTO> {

    private final OrdinationEditorViewModel viewModel;

    public DishesAdapter(Fragment fragment, RecyclerView view, OrdinationEditorViewModel viewModel) {
        super(fragment, view, SelectionType.NONE);
        this.viewModel = viewModel;
        viewModel.getCurrentOrdination().observe(fragment, (value) -> refreshViewHolders());
    }

    @NonNull
    @Override
    public DishViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DishViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.dish_item, parent, false));
    }

    public class DishViewHolder extends RecyclerViewAdapter<DishViewHolder, DishDTO>.IdentifiableViewHolder {

        private MaterialCardView dishContainer;
        private TextView dishView;

        private RecyclerView ordersView;
        private GroupsAdapter adapter;

        public DishViewHolder(View view) {
            super(view);
            dishContainer = view.findViewById(R.id.dishContainer);
            dishView = view.findViewById(R.id.dishView);

            ordersView = view.findViewById(R.id.ordersView);
            adapter = new GroupsAdapter(getOwner(), ordersView, viewModel);
            ordersView.setAdapter(adapter);
            ordersView.setLayoutManager(new LinearLayoutManager(getOwner().getContext()));
        }

        @Override
        public void updateView(DishDTO dto, boolean selected) {
            if(viewModel.getCurrentOrdination().getValue() != null) {
                dishView.setText(dto.getName() + " (" + viewModel.getCurrentOrdination().getValue().getQuantityOf(dto) + ")");
                adapter.updateData(viewModel.getCurrentOrdination().getValue().getGroups(dto));
            }
        }

        @Override
        public View.OnClickListener getClickListener(DishDTO dto) {
            return evt -> viewModel.addDish(dto);
        }

        @Override
        public View getMainView() {
            return dishContainer;
        }
    }
}
