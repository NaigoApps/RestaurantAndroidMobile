package com.naigoapps.restaurantmobile.common;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.selection.ItemKeyProvider;

import com.naigoapps.restaurantmobile.dto.Identifiable;

public class IdentifiableKeyProvider<I extends Identifiable> extends ItemKeyProvider<String> {

    private I[] data;

    protected IdentifiableKeyProvider() {
        super(ItemKeyProvider.SCOPE_MAPPED);
    }

    @Nullable
    @Override
    public String getKey(int position) {
        if (data != null) {
            if (0 <= position && position < data.length && data[position] != null) {
                return data[position].getUuid();
            }
        }
        return null;
    }

    @Override
    public int getPosition(@NonNull String key) {
        if (data != null) {
            I[] values = data;
            for (int i = 0; i < values.length; i++) {
                if (values[i] != null && key.equals(values[i].getUuid())) {
                    return i;
                }
            }
        }
        return -1;
    }

    public void setData(I[] data) {
        this.data = data;
    }
}
