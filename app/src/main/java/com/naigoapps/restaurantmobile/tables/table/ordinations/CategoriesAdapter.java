package com.naigoapps.restaurantmobile.tables.table.ordinations;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.naigoapps.restaurantmobile.R;
import com.naigoapps.restaurantmobile.dto.CategoryDTO;
import com.naigoapps.restaurantmobile.tables.RecyclerViewAdapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.selection.ItemDetailsLookup;
import androidx.recyclerview.widget.RecyclerView;

public class CategoriesAdapter extends RecyclerViewAdapter<CategoryViewHolder, CategoryDTO> {

    public CategoriesAdapter(RecyclerView view) {
        super(view, "categories");
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CategoryViewHolder(this, LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_fragment, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);

        CategoryDTO category = getData()[position];

        holder.setCategory(category);
    }

    @Override
    protected boolean onItemActivated(ItemDetailsLookup.ItemDetails key) {
        Bundle bundle = new Bundle();
        CategoryDTO dto = getData()[key.getPosition()];
        bundle.putString("uuid", dto.getUuid());
//        Navigation.findNavController(view).navigate(R.id.selectTable, bundle);
        return true;
    }
}
