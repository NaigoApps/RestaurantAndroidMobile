package com.naigoapps.restaurantmobile.common;

import androidx.annotation.NonNull;
import androidx.recyclerview.selection.SelectionTracker;

public class NoSelectionPredicate<T> extends SelectionTracker.SelectionPredicate<T> {

    @Override
    public boolean canSetStateForKey(@NonNull T key, boolean nextState) {
        return !nextState;
    }

    @Override
    public boolean canSetStateAtPosition(int position, boolean nextState) {
        return !nextState;
    }

    @Override
    public boolean canSelectMultiple() {
        return false;
    }
}
