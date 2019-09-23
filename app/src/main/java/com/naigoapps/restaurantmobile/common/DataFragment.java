package com.naigoapps.restaurantmobile.common;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.naigoapps.restaurantmobile.viewmodels.DataViewModel;

public abstract class DataFragment<T> extends Fragment {

    private DataViewModel<T> viewModel;

    public DataFragment() {
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(getActivity() != null) {
            viewModel = getViewModel();
        }
        viewModel.get(this, this::onChange);
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

    protected abstract void onChange(T data);

    protected abstract DataViewModel<T> getViewModel();

}
