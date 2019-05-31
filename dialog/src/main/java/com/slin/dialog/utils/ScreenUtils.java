package com.slin.dialog.utils;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * author: slin
 * date: 2019-05-30
 * description:
 */
public class ScreenUtils {

    /**
     * 获取屏幕宽度
     * @param context context
     * @return screen width 宽
     */
    public static int getScreenWidth(Context context){
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return displayMetrics.widthPixels;
    }

    /**
     * 获取屏幕高度
     * @param context context
     * @return screen height 高
     */
    public static int getScreenHeight(Context context){
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return displayMetrics.heightPixels;
    }

    /**
     *  单位px转为dp
     * @param context context
     * @param pxValue px value
     * @return dp value
     */
    public static int px2dp(Context context, int pxValue){
        final float density = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / density + 0.5);
    }

    /**
     * 单位dp转为px
     * @param context context
     * @param dpValue dp value
     * @return px value
     */
    public static int dp2px(Context context, int dpValue){
        final float density = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * density + 0.5);
    }

}
