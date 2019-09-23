package com.naigoapps.restaurantmobile.tables.table.ordinations.editing.dishes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.selection.ItemDetailsLookup;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.naigoapps.restaurantmobile.R;
import com.naigoapps.restaurantmobile.common.RecyclerViewAdapter;
import com.naigoapps.restaurantmobile.common.RemoteRecyclerViewAdapter;
import com.naigoapps.restaurantmobile.common.SelectionType;
import com.naigoapps.restaurantmobile.common.Utils;
import com.naigoapps.restaurantmobile.dto.AdditionDTO;
import com.naigoapps.restaurantmobile.viewmodels.AdditionsViewModel;

public class AdditionsListAdapter extends RemoteRecyclerViewAdapter<AdditionsListAdapter.AdditionViewHolder, AdditionsViewModel, AdditionDTO> {

    public AdditionsListAdapter(Fragment fragment, RecyclerView view) {
        super(fragment, view, SelectionType.NONE, additionsViewModel -> {
            //TODO
        });
    }

//    @Override
//    protected boolean onItemActivated(ItemDetailsLookup.ItemDetails key) {
//        String k = getKey(key.getPosition());
//        if (tracker.isSelected(k)) {
//            tracker.deselect(k);
//        } else {
//            tracker.select(k);
//        }
//        return true;
//    }

    @NonNull
    @Override
    public AdditionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdditionViewHolder(this, LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false));
    }

    @Override
    public AdditionsViewModel createViewModel(ViewModelProvider provider) {
        return provider.get(AdditionsViewModel.class);
    }

    public class AdditionViewHolder extends RecyclerViewAdapter<AdditionViewHolder, AdditionDTO>.IdentifiableViewHolder {

        private MaterialCardView cardView;
        private TextView textView;

        public AdditionViewHolder(AdditionsListAdapter adapter, View v) {
            super(v);
            cardView = v.findViewById(R.id.itemCard);
            textView = v.findViewById(R.id.itemText);
        }

        public void bind(AdditionDTO addition) {
            textView.setText(addition.getName());
        }

        @Override
        public View getMainView() {
            return cardView;
        }

        @Override
        public View.OnClickListener getClickListener(AdditionDTO dto) {
            return null;
        }

        @Override
        public void updateView(AdditionDTO dto, boolean selected) {
            cardView.setActivated(selected);
            if (cardView.isActivated()) {
                cardView.setCardElevation(4);
                cardView.setCardBackgroundColor(Utils.color(cardView.getContext(), R.color.colorAccent));
            } else {
                cardView.setCardElevation(1);
                cardView.setCardBackgroundColor(Utils.color(cardView.getContext(), R.color.colorTransparent));
            }
        }
    }
}
