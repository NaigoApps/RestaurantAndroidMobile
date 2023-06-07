package com.naigoapps.restaurantmobile.tables.messages;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.naigoapps.restaurantmobile.R;
import com.naigoapps.restaurantmobile.common.RecyclerViewAdapter;
import com.naigoapps.restaurantmobile.common.SelectionType;
import com.naigoapps.restaurantmobile.dto.LocationDTO;

public class CheckableLocationsAdapter extends RecyclerViewAdapter<CheckableLocationsAdapter.CheckableLocationsViewHolder, LocationDTO> {

    public CheckableLocationsAdapter(Fragment owner, RecyclerView view) {
        super(owner, view, SelectionType.MULTIPLE);
    }

    @NonNull
    @Override
    public CheckableLocationsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CheckableLocationsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.checkbox_item, parent, false));
    }

    public class CheckableLocationsViewHolder extends RecyclerViewAdapter<CheckableLocationsViewHolder, LocationDTO>.IdentifiableViewHolder {

        private CheckBox checkBox;

        public CheckableLocationsViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.check_item);
        }

        @Override
        public View getMainView() {
            return checkBox;
        }

        @Override
        public void updateView(LocationDTO dto, boolean selected) {
            checkBox.setText(dto.getName());
            checkBox.setChecked(selected);
            checkBox.setOnClickListener((evt) -> toggleSelection(dto.getUuid()));
        }

        @Override
        public View.OnClickListener getClickListener(LocationDTO dto) {
            return null;
        }
    }

}
