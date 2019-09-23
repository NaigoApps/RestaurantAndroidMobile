package com.naigoapps.restaurantmobile;

import java.lang.ref.WeakReference;

import androidx.fragment.app.FragmentActivity;

public class Application {
    private static WeakReference<FragmentActivity> mainActivity;

    public static void setActivity(FragmentActivity activity){
        mainActivity = new WeakReference<>(activity);
    }

    public static FragmentActivity getActivity(){
        if(mainActivity != null) {
            return mainActivity.get();
        }
        return null;
    }
}
