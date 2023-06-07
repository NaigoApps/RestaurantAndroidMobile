package com.naigoapps.restaurantmobile.viewmodels;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.naigoapps.restaurantmobile.Application;

public abstract class DataViewModel<T> extends ViewModel {

    private MutableLiveData<T> data;

    public DataViewModel() {
        data = new MutableLiveData<>();
    }

    public void get(@NonNull LifecycleOwner owner, Observer<T> observer) {
        data.observe(owner, observer);
    }

    public void set(T data) {
        this.data.setValue(data);
    }

    public MutableLiveData<T> getData() {
        return data;
    }
}
