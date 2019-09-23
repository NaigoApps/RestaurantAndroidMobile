package com.naigoapps.restaurantmobile.rs.cache;

import android.content.Context;

import com.naigoapps.restaurantmobile.dto.DTO;

import java.util.HashMap;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public abstract class DTOCache<D extends DTO> extends ViewModel {

    private MutableLiveData<List<D>> entities;
    private HashMap<String, D> entitiesMap;

    public LiveData<List<D>> getEntities(Fragment fragment) {
        mayLoadEntities(fragment.getContext());
        return entities;
    }

    public LiveData<D> getEntity(Fragment fragment, String uuid) {
        MutableLiveData<D> result = new MutableLiveData<>();
        mayLoadEntities(fragment.getContext());
        entities.observe(fragment, tables -> {
            if (entitiesMap != null) {
                result.postValue(entitiesMap.get(uuid));
            }
        });
        return result;
    }

    private void mayLoadEntities(Context context) {
        if (entities == null) {
            entities = new MutableLiveData<>();
            loadEntities(context);
        }
    }

    protected void onLoadComplete(List<D> entities){
        entitiesMap = new HashMap<>();
        for (D dto : entities) {
            entitiesMap.put(dto.getUuid(), dto);
        }
        this.entities.postValue(entities);
    }

    protected abstract void loadEntities(Context context);

}
