package com.naigoapps.restaurantmobile.common;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.naigoapps.restaurantmobile.dto.Identifiable;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class RecyclerViewAdapter<VH extends RecyclerViewAdapter.IdentifiableViewHolder, D extends Identifiable> extends RecyclerView.Adapter<VH> {

    private WeakReference<Fragment> owner;

    private SelectionType selectionType;
    protected RecyclerView view;
    private D[] dtos;

    private SelectionViewModel selectionViewModel;

    private Map<String, VH> holdersMap;

    public RecyclerViewAdapter(Fragment owner, RecyclerView view, SelectionType selectionType) {
        this.owner = new WeakReference<>(owner);
        holdersMap = new HashMap<>();
        this.view = view;
        if (owner != null) {
            selectionViewModel = ViewModelProviders.of(owner).get(SelectionViewModel.class);
        }
        this.selectionType = selectionType;
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.bind(dtos[position].getUuid());
//        holdersMap.put(dtos[position].getUuid(), holder);
    }

    @Override
    public void onViewAttachedToWindow(@NonNull VH holder) {
        super.onViewAttachedToWindow(holder);
        holdersMap.put(holder.getKey(), holder);
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull VH holder) {
        super.onViewDetachedFromWindow(holder);
        holdersMap.remove(holder.getKey());
    }

    @Override
    public int getItemCount() {
        return dtos != null ? dtos.length : 0;
    }

    public void updateData(D[] ds) {
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(
                new IdentifiableCallback(dtos, ds));
        dtos = ds;
        result.dispatchUpdatesTo(this);

        notifyDataSetChanged();
    }

    public D[] getData() {
        return dtos;
    }

    public boolean isSelected(String key) {
        return selectionViewModel != null && selectionViewModel.isSelected(key);
    }

    public boolean hasSelection() {
        return selectionViewModel != null && selectionViewModel.hasSelection();
    }

    public D getSelectedData() {
        if (selectionViewModel != null && selectionViewModel.hasSelection()) {
            return find(selectionViewModel.getSelection().iterator().next());
        }
        return null;
    }

    public List<D> getSelectedDatas() {
        if (selectionViewModel == null) {
            return Collections.emptyList();
        }
        List<D> selectedItems = new ArrayList<>();
        for (String id : selectionViewModel.getSelection()) {
            selectedItems.add(find(id));
        }
        return selectedItems;
    }

    public String getSelection() {
        if (selectionViewModel == null) {
            return null;
        }
        return selectionViewModel.getSelection().iterator().next();
    }

    public void clearSelection() {
        if (selectionViewModel != null) {
            selectionViewModel.clear();
        }
    }

    public void setSelection(String key) {
        if (selectionType == SelectionType.NONE) {
            return;
        }
        selectionViewModel.selectOnly(key);
    }

    public void select(String key) {
        if (!isSelected(key)) {
            toggleSelection(key);
        }
    }

    public void toggleSelection(String key) {
        if (selectionType == SelectionType.NONE) {
            return;
        }

        if (selectionType == SelectionType.FORCE_ONE) {
            selectionViewModel.selectOnly(key);
            return;
        }

        D data = find(key);
        if (data == null) {
            return;
        }
        if (selectionViewModel.isSelected(key)) {
            selectionViewModel.deselect(key);
        } else {
            if (selectionType == SelectionType.ONE) {
                selectionViewModel.selectOnly(key);
            } else {
                selectionViewModel.select(key);
            }
        }
    }

    public void observeSelection(LifecycleOwner owner, Observer<Set<D>> observer) {
        selectionViewModel.observe(owner, ids -> observer.onChanged(new HashSet<>(getSelectedDatas())));
    }

    private D find(String key) {
        if (dtos == null) {
            return null;
        }
        for (D data : dtos) {
            if (data.getUuid().equals(key)) {
                return data;
            }
        }
        return null;
    }

    public void refreshViewHolders() {
        for(Map.Entry<String, VH> holder : holdersMap.entrySet()){
            holder.getValue().refreshView(selectionViewModel.isSelected(holder.getKey()));
        }
    }

    public abstract class IdentifiableViewHolder extends RecyclerView.ViewHolder {

        private String key;
        private D dto;

        public IdentifiableViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bind(String key) {
            this.key = key;
            this.dto = find(key);
            updateView(dto, isSelected(key));
            if (getMainView() != null) {
                getMainView().setOnClickListener(v -> {
                    View.OnClickListener customListener = getClickListener(find(key));
                    if (!hasSelection() && customListener != null) {
                        customListener.onClick(v);
                    } else {
                        toggleSelection(key);
                    }
                    for (Map.Entry<String, VH> entry : holdersMap.entrySet()) {
                        entry.getValue().refreshView(isSelected(entry.getKey()));
                    }
                });
                getMainView().setOnLongClickListener(v -> {
                    toggleSelection(key);
                    for (Map.Entry<String, VH> entry : holdersMap.entrySet()) {
                        entry.getValue().refreshView(isSelected(entry.getKey()));
                    }
                    return true;
                });
            }
        }

        public abstract View getMainView();

        public void refreshView(boolean isSelected) {
            updateView(dto, isSelected);
        }

        public String getKey() {
            return key;
        }

        public abstract void updateView(D dto, boolean selected);

        public abstract View.OnClickListener getClickListener(D dto);

        public Fragment getOwner(){
            return owner.get();
        }
    }
}
