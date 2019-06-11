package com.naigoapps.restaurantmobile.tables;

import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.selection.ItemDetailsLookup;
import androidx.recyclerview.widget.RecyclerView;

public class IdentifiableDetailLookup extends ItemDetailsLookup<String> {

    private RecyclerView recyclerView;

    public IdentifiableDetailLookup(RecyclerView view) {
        this.recyclerView = view;
    }

    @Nullable
    @Override
    public ItemDetails<String> getItemDetails(@NonNull MotionEvent e) {
        View view = recyclerView.findChildViewUnder(e.getX(), e.getY());
        if (view != null) {
            RecyclerView.ViewHolder viewHolder = recyclerView.getChildViewHolder(view);
            if (viewHolder instanceof IdentifiableViewHolder) {
                return ((IdentifiableViewHolder) viewHolder).getItemDetails();
            }
        }
        return null;
    }
}
