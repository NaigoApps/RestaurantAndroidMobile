package com.naigoapps.restaurantmobile.tables.table.ordinations.editing.categories;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.naigoapps.restaurantmobile.R;
import com.naigoapps.restaurantmobile.common.RecyclerViewAdapter;
import com.naigoapps.restaurantmobile.common.SelectionType;
import com.naigoapps.restaurantmobile.dto.CategoryDTO;
import com.naigoapps.restaurantmobile.viewmodels.OrdinationEditorViewModel;

public class CategoriesAdapter extends RecyclerViewAdapter<CategoriesAdapter.CategoryViewHolder, CategoryDTO> {

    private OrdinationEditorViewModel viewModel;

    public CategoriesAdapter(Fragment fragment, RecyclerView view, OrdinationEditorViewModel viewModel) {
        super(fragment, view, SelectionType.NONE);
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CategoryViewHolder(this, LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item, parent, false));
    }

    public class CategoryViewHolder extends RecyclerViewAdapter<CategoryViewHolder, CategoryDTO>.IdentifiableViewHolder {

        private MaterialCardView categoryViewContainer;
        private TextView categoryView;

        public CategoryViewHolder(CategoriesAdapter adapter, View v) {
            super(v);
            categoryViewContainer = v.findViewById(R.id.categoryContainer);
            categoryView = v.findViewById(R.id.groupLabel);
        }

        @Override
        public View.OnClickListener getClickListener(CategoryDTO dto) {
            viewModel.setSelectedCategory(dto);
            return Navigation.createNavigateOnClickListener(R.id.selectCategory, CategoriesMenuFragmentDirections
                    .selectCategory(dto.getUuid(), dto.getName())
                    .getArguments());
        }

        @Override
        public View getMainView() {
            return categoryView;
        }

        @Override
        public void updateView(CategoryDTO dto, boolean selected) {
            categoryView.setText(dto.getName() + " (" + viewModel.getQuantityOf(dto) + ")");
        }
    }

}
