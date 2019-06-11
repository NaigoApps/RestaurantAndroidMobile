package com.naigoapps.restaurantmobile.tables.table.ordinations;

import android.view.View;
import android.widget.TextView;

import com.google.android.material.card.MaterialCardView;
import com.naigoapps.restaurantmobile.R;
import com.naigoapps.restaurantmobile.dto.CategoryDTO;
import com.naigoapps.restaurantmobile.dto.OrdinationDTO;
import com.naigoapps.restaurantmobile.dto.PhaseOrdersDTO;
import com.naigoapps.restaurantmobile.tables.IdentifiableViewHolder;
import com.naigoapps.restaurantmobile.tables.table.OrdinationsAdapter;
import com.naigoapps.restaurantmobile.tables.table.PhasesAdapter;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CategoryViewHolder extends IdentifiableViewHolder {

    private TextView categoryTitleView;
    private RecyclerView dishesList;

    public CategoryViewHolder(CategoriesAdapter adapter, View v) {
        super(adapter, v);
        categoryTitleView = v.findViewById(R.id.categoryTitleView);
        dishesList = v.findViewById(R.id.dishesList);
    }

    public void setCategory(CategoryDTO category){
        categoryTitleView.setText(category.getName());
//TODO DO FOR DISHES
//        PhasesAdapter adapter = new PhasesAdapter(phasesList);
//        adapter.updateData(category.getOrders().toArray(new PhaseOrdersDTO[0]));
//        phasesList.setAdapter(adapter);
    }

}