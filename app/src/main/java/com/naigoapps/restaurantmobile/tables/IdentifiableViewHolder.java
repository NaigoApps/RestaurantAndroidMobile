package com.naigoapps.restaurantmobile.tables;


import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.selection.ItemDetailsLookup;
import androidx.recyclerview.widget.RecyclerView;

public abstract class IdentifiableViewHolder extends RecyclerView.ViewHolder {

    private RecyclerViewAdapter adapter;

    public IdentifiableViewHolder(RecyclerViewAdapter adapter, @NonNull View itemView) {
        super(itemView);
        this.adapter = adapter;
    }

    public void setSelected(boolean selected) {
        itemView.setActivated(selected);
    }

    public ItemDetailsLookup.ItemDetails<String> getItemDetails() {
        return new IdentifiableItemDetails(getAdapterPosition(), adapter.getKey(getAdapterPosition()));
    }

    public class IdentifiableItemDetails extends ItemDetailsLookup.ItemDetails<String> {

        private int position;
        private String key;

        public IdentifiableItemDetails(int position, String key) {
            this.position = position;
            this.key = key;
        }

        @Override
        public int getPosition() {
            return position;
        }

        @Nullable
        @Override
        public String getSelectionKey() {
            return key;
        }
    }

}