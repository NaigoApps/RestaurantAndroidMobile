package com.naigoapps.restaurantmobile.common;

import android.content.Context;
import android.os.Build;

public class Utils {

    public static String string(Object obj) {
        return obj != null ? obj.toString() : "?";
    }

    public static int color(Context context, int id) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return context.getResources().getColor(id);
        } else {
            return context.getColor(id);
        }
    }
}
