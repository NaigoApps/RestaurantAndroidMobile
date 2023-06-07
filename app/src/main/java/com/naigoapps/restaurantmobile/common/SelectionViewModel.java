package com.naigoapps.restaurantmobile.common;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class SelectionViewModel extends ViewModel {

    private MutableLiveData<List<String>> selectedItems;

    public SelectionViewModel() {
        selectedItems = new MutableLiveData<>();
        selectedItems.setValue(new ArrayList<>());
    }

    public void select(String data) {
        List<String> newSelection = new ArrayList<>(selectedItems.getValue());
        if (newSelection.add(data)) {
            selectedItems.setValue(newSelection);
        }
    }

    public void selectOnly(String data) {
        List<String> newSelection = new ArrayList<>();
        newSelection.add(data);
        selectedItems.setValue(newSelection);
    }

    public void deselect(String data) {
        List<String> newSelection = new ArrayList<>(selectedItems.getValue());
        if (newSelection.remove(data)) {
            selectedItems.setValue(newSelection);
        }
    }

    public void toggle(String data) {
        List<String> newSelection = new ArrayList<>(selectedItems.getValue());
        if (newSelection.contains(data)) {
            newSelection.remove(data);
        } else {
            newSelection.add(data);
        }
        selectedItems.setValue(newSelection);
    }

    public void clear() {
        selectedItems.setValue(new ArrayList<>());
    }

    public boolean isSelected(String data) {
        return selectedItems.getValue().contains(data);
    }

    public boolean hasSelection() {
        return !selectedItems.getValue().isEmpty();
    }

    public List<String> getSelection() {
        return new ArrayList<>(selectedItems.getValue());
    }

    public void observe(LifecycleOwner owner, Observer<List<String>> observer) {
        selectedItems.observe(owner, observer);
    }
}
