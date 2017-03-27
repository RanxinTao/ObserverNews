package com.rtao.observernews.utils;

import android.content.Context;

/**
 * Unit conversion tool:
 * dip, px conversion tool
 */

public class DensityUtil {
    /**
     * Convert dip to px based on the phone resolution
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * Convert px to dip based on the phone resolution
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
