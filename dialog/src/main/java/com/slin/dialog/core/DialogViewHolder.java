package com.slin.dialog.core;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * author: slin
 * date: 2019-05-29
 * description: dialog ViewHolder
 */
public abstract class DialogViewHolder {

    @LayoutRes
    protected int mLayoutResId;
    protected BaseSlinDialog mDialog;
    private ViewHolder mViewHolder;

    public DialogViewHolder(@LayoutRes int layoutResId){
        this.mLayoutResId = layoutResId;
        mViewHolder = new ViewHolder();
    }

    public View onConvertView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container){
        View view = inflater.inflate(mLayoutResId, container, false);
        mViewHolder.setConvertView(view);
        onViewConverted(view, mViewHolder);
        return view;
    }

    protected abstract void onViewConverted(View view, ViewHolder helper);

    public void attach(BaseSlinDialog dialog) {
        this.mDialog = dialog;
    }

    public void detach(){
        mDialog = null;
    }

    public BaseSlinDialog getDialog() {
        return mDialog;
    }

}
