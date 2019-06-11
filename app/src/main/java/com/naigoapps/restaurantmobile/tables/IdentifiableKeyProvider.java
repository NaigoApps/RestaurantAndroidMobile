package com.naigoapps.restaurantmobile.tables;

import com.naigoapps.restaurantmobile.dto.Identifiable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.selection.ItemKeyProvider;

public class IdentifiableKeyProvider<I extends Identifiable> extends ItemKeyProvider<String> {

    private MutableLiveData<I[]> data;

    protected IdentifiableKeyProvider(MutableLiveData<I[]> data) {
        super(ItemKeyProvider.SCOPE_MAPPED);
        this.data = data;
    }

    @Nullable
    @Override
    public String getKey(int position) {
        if (data.getValue() != null) {
            I[] values = data.getValue();
            if (0 <= position && position < values.length && values[position] != null) {
                return values[position].getUuid();
            }
        }
        return null;
    }

    @Override
    public int getPosition(@NonNull String key) {
        if (data.getValue() != null) {
            I[] values = data.getValue();
            for (int i = 0; i < values.length; i++) {
                if (values[i] != null && key.equals(values[i].getUuid())) {
                    return i;
                }
            }
        }
        return -1;
    }
}
