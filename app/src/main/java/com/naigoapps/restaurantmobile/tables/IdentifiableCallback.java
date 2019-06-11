package com.naigoapps.restaurantmobile.tables;

import com.naigoapps.restaurantmobile.dto.Identifiable;

import androidx.recyclerview.widget.DiffUtil;

public class IdentifiableCallback extends DiffUtil.Callback {

    private Identifiable[] oldList;
    private Identifiable[] newList;

    public IdentifiableCallback(Identifiable[] oldList, Identifiable[] newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList == null ? 0 : oldList.length;
    }

    @Override
    public int getNewListSize() {
        return newList == null ? 0 : newList.length;
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList[oldItemPosition].getUuid().equals(newList[newItemPosition].getUuid());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList[oldItemPosition] == newList[newItemPosition];
    }
}
