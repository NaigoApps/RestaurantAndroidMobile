package com.naigoapps.restaurantmobile.common;

import android.text.TextWatcher;

public abstract class TextWatcherAdapter implements TextWatcher {
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        //Nothing to do
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        //Nothing to do
    }

}
