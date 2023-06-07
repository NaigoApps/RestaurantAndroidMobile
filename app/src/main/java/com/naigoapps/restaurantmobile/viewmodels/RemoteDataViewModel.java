package com.naigoapps.restaurantmobile.viewmodels;

import androidx.core.util.Consumer;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.naigoapps.restaurantmobile.RemoteLoadTask;

public abstract class RemoteDataViewModel<T> extends ViewModel {

    private MutableLiveData<T> data;

    public RemoteDataViewModel() {
        data = new MutableLiveData<>();
    }

    public void register(LifecycleOwner owner, Observer<T> observer) {
        data.observe(owner, observer);
    }

    public void refresh() {
        loadImpl();
    }

    public T getData() {
        return data.getValue();
    }

    private void loadImpl() {
        RemoteLoadTask<T> task = createTask(result -> data.postValue(result));
        if(task != null) {
            task.execute();
        }
    }

    protected abstract RemoteLoadTask<T> createTask(Consumer<T> consumer);
}
