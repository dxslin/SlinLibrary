package com.slin.dialog;

import android.graphics.drawable.Drawable;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.IdRes;

import com.slin.dialog.utils.Preconditions;

/**
 * author: slin
 * date: 2019-05-31
 * description: ViewHolder
 */
public class ViewHolder {

    private SparseArray<View> views = new SparseArray<>();
    private View convertView;

    public ViewHolder(){

    }

    public ViewHolder(View view){
        this.convertView = view;
    }


    public void setConvertView(View convertView) {
        this.convertView = convertView;
    }

    /**
     * 获取View
     * @param viewId viewId
     * @param <T> type extends View
     * @return the view
     */
    @SuppressWarnings("unchecked")
    public  <T extends View> T getView(@IdRes int viewId){
        Preconditions.checkNotNull(convertView, "convertView is required,  please set convertView");
        View view = views.get(viewId);
        if(view == null){
            view = convertView.findViewById(viewId);
            views.put(viewId, view);
        }
        return (T) view;
    }

    /**
     * 设置图像
     * @param viewId viewId
     * @param drawable 图片资源
     */
    public ViewHolder setDrawable(@IdRes int viewId, Drawable drawable){
        ImageView imageView = getView(viewId);
        imageView.setImageDrawable(drawable);
        return this;
    }

    /**
     * 设置文本
     * @param viewId viewId
     * @param text text
     */
    public ViewHolder setText(@IdRes int viewId, CharSequence text){
        TextView view = getView(viewId);
        view.setText(text);
        return this;
    }

    /**
     * 设置文本颜色
     * @param viewId viewId
     * @param colorId colorId
     */
    public ViewHolder setTextColor(@IdRes int viewId, @ColorInt int colorId){
        TextView view = getView(viewId);
        view.setTextColor(colorId);
        return this;
    }

    /**
     * 设置背景颜色
     * @param viewId colorId
     * @param colorId colorId
     */
    public ViewHolder setBackgroundColor(@IdRes int viewId, @ColorInt int colorId){
        View view = getView(viewId);
        view.setBackgroundColor(colorId);
        return this;
    }

    /**
     * 设置背景
     * @param viewId viewId
     * @param resId resId
     */
    public ViewHolder setBackgroundResource(@IdRes int viewId, @DrawableRes int resId){
        View view = getView(viewId);
        view.setBackgroundResource(resId);
        return this;
    }

    /**
     * 设置点击事件监听
     * @param viewId viewId
     * @param listener listener
     */
    public ViewHolder setOnClickListener(@IdRes int viewId, View.OnClickListener listener){
        View view = getView(viewId);
        view.setOnClickListener(listener);
        return this;
    }

}
