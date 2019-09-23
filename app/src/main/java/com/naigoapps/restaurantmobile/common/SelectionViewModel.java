package com.naigoapps.restaurantmobile.common;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import java.util.HashSet;
import java.util.Set;

public class SelectionViewModel extends ViewModel {

    private MutableLiveData<Set<String>> selectedItems;

    public SelectionViewModel() {
        selectedItems = new MutableLiveData<>();
        selectedItems.setValue(new HashSet<>());
    }

    public void select(String data) {
        Set<String> newSelection = new HashSet<>(selectedItems.getValue());
        if (newSelection.add(data)) {
            selectedItems.setValue(newSelection);
        }
    }

    public void selectOnly(String data) {
        Set<String> newSelection = new HashSet<>();
        newSelection.add(data);
        selectedItems.setValue(newSelection);
    }

    public void deselect(String data) {
        Set<String> newSelection = new HashSet<>(selectedItems.getValue());
        if (newSelection.remove(data)) {
            selectedItems.setValue(newSelection);
        }
    }

    public void toggle(String data) {
        Set<String> newSelection = new HashSet<>(selectedItems.getValue());
        if (newSelection.contains(data)) {
            newSelection.remove(data);
        } else {
            newSelection.add(data);
        }
        selectedItems.setValue(newSelection);
    }

    public void clear() {
        selectedItems.setValue(new HashSet<>());
    }

    public boolean isSelected(String data) {
        return selectedItems.getValue().contains(data);
    }

    public boolean hasSelection() {
        return !selectedItems.getValue().isEmpty();
    }

    public Set<String> getSelection() {
        return new HashSet<>(selectedItems.getValue());
    }

    public void observe(LifecycleOwner owner, Observer<Set<String>> observer) {
        selectedItems.observe(owner, observer);
    }
}
