package com.naigoapps.restaurantmobile.tables;

import android.content.DialogInterface;
import android.os.Bundle;

import com.naigoapps.restaurantmobile.viewmodels.RemoteViewModel;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

public abstract class RemoteDataFragment<T> extends Fragment {

    private RemoteViewModel<T> viewModel;

    public RemoteDataFragment() {
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(getActivity() != null) {
            viewModel = getViewModel();
            viewModel.get(getActivity(), this::onLoad);
        }
    }

    public void refresh() {
        viewModel.load(getActivity(), this::onLoad);
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

    protected abstract RemoteViewModel<T> getViewModel();

}
