package com.rtao.observernews.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Cache some parameters and data of the application
 */

public class CacheUtils {

    /**
     * Cache text data
     * @param context
     * @param key
     * @param value
     */
    public static void putString(Context context, String key, String value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("observerNews",
                Context.MODE_PRIVATE);
        sharedPreferences.edit().putString(key, value).commit();
    }

    /**
     * Retrieve cached text data
     * @param context
     * @param key
     * @return
     */
    public static String getString(Context context, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("observerNews",
                Context.MODE_PRIVATE);
        return sharedPreferences.getString(key, "");
    }
}
