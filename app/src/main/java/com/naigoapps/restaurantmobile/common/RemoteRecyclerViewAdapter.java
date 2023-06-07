package com.naigoapps.restaurantmobile.common;

import androidx.core.util.Consumer;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.naigoapps.restaurantmobile.dto.Identifiable;
import com.naigoapps.restaurantmobile.viewmodels.RemoteDataViewModel;

public abstract class RemoteRecyclerViewAdapter<VH extends RecyclerViewAdapter.IdentifiableViewHolder, VM extends RemoteDataViewModel<D[]>, D extends Identifiable>
        extends RecyclerViewAdapter<VH, D> {

    protected VM viewModel;

    public RemoteRecyclerViewAdapter(Fragment fragment, RecyclerView view, SelectionType selectionType, Consumer<VM> viewModelInitializer) {
        super(fragment, view, selectionType);
        this.viewModel = createViewModel(ViewModelProviders.of(fragment));
        if (viewModelInitializer != null) {
            viewModelInitializer.accept(this.viewModel);
        }
        this.viewModel.register(fragment, this::updateData);
    }

    public void refresh() {
        this.viewModel.refresh();
    }

    public abstract VM createViewModel(ViewModelProvider provider);
}
