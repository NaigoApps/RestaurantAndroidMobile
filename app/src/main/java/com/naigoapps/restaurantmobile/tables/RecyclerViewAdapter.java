package com.naigoapps.restaurantmobile.tables;

import android.content.Context;

import com.naigoapps.restaurantmobile.dto.Identifiable;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.selection.ItemDetailsLookup;
import androidx.recyclerview.selection.OnItemActivatedListener;
import androidx.recyclerview.selection.Selection;
import androidx.recyclerview.selection.SelectionPredicates;
import androidx.recyclerview.selection.SelectionTracker;
import androidx.recyclerview.selection.StorageStrategy;
import androidx.recyclerview.widget.RecyclerView;

public abstract class RecyclerViewAdapter<VH extends IdentifiableViewHolder, D extends Identifiable> extends RecyclerView.Adapter<VH> {

    protected Context context;
    protected RecyclerView view;
    private String resource;

    private MutableLiveData<D[]> dtos;

    protected SelectionTracker<String> tracker;

    public RecyclerViewAdapter(RecyclerView view, String resource) {
        this.view = view;
        this.context = view.getContext();
        this.resource = resource;
        this.dtos = new MutableLiveData<>();
    }

    public void initSelectionTracker(SelectionTracker.SelectionObserver observer) {
        this.tracker = new SelectionTracker.Builder<>(
                resource,
                view,
                new IdentifiableKeyProvider<>(dtos),
                new IdentifiableDetailLookup(view),
                StorageStrategy.createStringStorage())
                .withSelectionPredicate(SelectionPredicates.createSelectSingleAnything())
                .withOnItemActivatedListener((value, event) -> onItemActivated(value))
                .build();
        this.tracker.addObserver(observer);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.setSelected(tracker != null && tracker.isSelected(getKey(position)));
    }

    @Override
    public int getItemCount() {
        return dtos.getValue() != null ? dtos.getValue().length : 0;
    }

    public String getKey(int position) {
        return dtos.getValue() != null ? dtos.getValue()[position].getUuid() : null;
    }

    public void updateData(D[] ds) {
        dtos.setValue(ds);
    }

    public D[] getData() {
        return dtos.getValue();
    }

    public boolean isSelected(String key) {
        return tracker.isSelected(key);
    }

    public boolean hasSelection() {
        return tracker.hasSelection();
    }

    public String getSelection(){
        return tracker.getSelection().iterator().next();
    }

    public void clearSelection() {
        tracker.clearSelection();
    }

    protected abstract boolean onItemActivated(ItemDetailsLookup.ItemDetails key);
}
