package com.slin.dialog.core;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.slin.dialog.utils.Preconditions;

/**
 * author: slin
 * date: 2019-05-29
 * description: dialog ViewHolder
 */
public abstract class DialogViewHolder implements OnViewConvertedListener {

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
        onViewConverted(mViewHolder, mDialog);
        return view;
    }

    public void attach(BaseSlinDialog dialog) {
        this.mDialog = dialog;
    }

    public void detach(){
        mDialog = null;
    }

    public BaseSlinDialog getDialog() {
        return mDialog;
    }

    /**
     * 通过Builder创建 DialogViewHolder，避免再次创建一个DialogViewHolder文件
     */
    public static class Builder {

        private int layoutResId;
        private OnViewConvertedListener onViewConvertedListener;

        public Builder layoutResId(@LayoutRes int layoutResId) {
            this.layoutResId = layoutResId;
            return this;
        }

        public Builder onViewConvertedListener(OnViewConvertedListener listener) {
            this.onViewConvertedListener = listener;
            return this;
        }

        public DialogViewHolder build() {
            Preconditions.checkNotNull(layoutResId, "layoutResId is required");
            Preconditions.checkNotNull(onViewConvertedListener, "onViewConvertedListener is required");
            return new DialogViewHolder(layoutResId) {
                @Override
                public void onViewConverted(ViewHolder helper, BaseSlinDialog dialog) {
                    onViewConvertedListener.onViewConverted(helper, dialog);
                }
            };
        }

    }

}
