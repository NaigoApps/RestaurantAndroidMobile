package com.naigoapps.restaurantmobile.viewmodels;

import com.naigoapps.restaurantmobile.RemoteLoadTask;

import androidx.core.util.Consumer;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

public abstract class RemoteViewModel<T> extends ViewModel {

    private MutableLiveData<T> data;

    public RemoteViewModel(){
        data = new MutableLiveData<>();
    }

    public void get(FragmentActivity activity, Observer<T> observer){
        data.observe(activity, observer);
        if(data.getValue() == null){
            loadImpl(activity);
        }
    }

    public void load(FragmentActivity activity, Observer<T> observer){
        data.observe(activity, observer);
        loadImpl(activity);
    }

    private void loadImpl(FragmentActivity activity){
        RemoteLoadTask<T> task = createTask(activity, result -> data.postValue(result));
        task.execute();
    }

    protected abstract RemoteLoadTask<T> createTask(FragmentActivity owner, Consumer<T> consumer);
}
