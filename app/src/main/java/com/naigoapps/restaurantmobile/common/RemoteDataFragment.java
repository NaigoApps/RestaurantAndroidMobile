package com.naigoapps.restaurantmobile.common;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.naigoapps.restaurantmobile.viewmodels.RemoteDataViewModel;

public abstract class RemoteDataFragment<T> extends Fragment {

    private RemoteDataViewModel<T> viewModel;

    public RemoteDataFragment() {
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = createViewModel();
        viewModel.register(this, this::onLoad);
        viewModel.refresh();
    }

    public void refresh() {
        viewModel.refresh();
    }

    public void showConfirmDialog(String message, DialogInterface.OnClickListener onConfirm) {
        if (getContext() != null) {
            new AlertDialog.Builder(getContext())
                    .setMessage(message)
                    .setPositiveButton("Sì", onConfirm)
                    .setNegativeButton("No", null)
                    .show();
        }
    }

    protected abstract void onLoad(T data);

    public T getData(){
        return viewModel.getData();
    }

    protected abstract RemoteDataViewModel<T> createViewModel();

}
