package com.slin.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.FloatRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.slin.dialog.utils.ScreenUtils;


/**
 * author: slin
 * date: 2019-05-24
 * description: dialog基类
 */
public class BaseDialog extends DialogFragment {

    private static final String TAG = BaseDialog.class.getSimpleName();

    private Context mContext;
    private float mDimAmount = 0.5f;    //外部阴影
    private boolean isShowBottomEnable; //是否在底部显示
    private int mMargin = 0;            //左右边距
    private int mAnimStyle;             //进入退出动画
    private boolean mOutCancel = true;  //点击外部取消
    private int mWidth;
    private int mHeight;

    private DialogViewHolder mDialogViewHolder;

    public static BaseDialog newInstance() {
        return new BaseDialog();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
        mDialogViewHolder.attach(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mDialogViewHolder.detach();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_TITLE, R.style.BaseDialog);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return mDialogViewHolder.onConvertView(inflater, container);
    }

    @Override
    public void onStart() {
        super.onStart();
        initParams();
    }


    private void initParams(){
        Window window = getDialog().getWindow();
        if(window != null){
            WindowManager.LayoutParams params = window.getAttributes();
            params.dimAmount = mDimAmount;
            if(isShowBottomEnable){
                params.gravity = Gravity.BOTTOM;
            }
            if (mWidth == 0){
                if(mMargin == 0){
                    params.width = WindowManager.LayoutParams.WRAP_CONTENT;
                } else {
                    params.width = ScreenUtils.getScreenWidth(mContext) - 2 * ScreenUtils.dp2px(mContext, mMargin);
                }
            } else {
                params.width = mWidth;
            }
            if(mHeight == 0){
                params.height = WindowManager.LayoutParams.WRAP_CONTENT;
            } else {
                params.height = ScreenUtils.dp2px(mContext, mHeight);
            }
            if(mAnimStyle != 0){
                window.setWindowAnimations(mAnimStyle);
            }
            window.setAttributes(params);
        }
        setCancelable(mOutCancel);
    }


    public BaseDialog setDialogViewHolder(DialogViewHolder mDialogViewHolder) {
        this.mDialogViewHolder = mDialogViewHolder;
        return this;
    }


    /**
     * 设置背景昏暗度
     * @param dimAmount 昏暗度（0.0 - 1.0）
     * @return this dialog
     */
    public BaseDialog setDimAmount(@FloatRange(from = 0, to = 1) float dimAmount){
        this.mDimAmount = dimAmount;
        return this;
    }

    /**
     * 是否显示在底部
     * @param showBottomEnable true 显示在底部
     * @return this dialog
     */
    public BaseDialog setShowBottomEnable(boolean showBottomEnable) {
        isShowBottomEnable = showBottomEnable;
        return this;
    }

    /**
     * 设置dialog的宽高
     * @param width width
     * @param height height
     * @return this dialog
     */
    public BaseDialog setSize(int width, int height){
        this.mWidth = width;
        this.mHeight = height;
        return this;
    }

    /**
     * 设置左右边距
     * @param mMargin margin
     * @return this dialog
     */
    public BaseDialog setMargin(int mMargin) {
        this.mMargin = mMargin;
        return this;
    }

    /**
     * 设置进入退出动画
     * @param animStyle anim resource
     * @return this dialog
     */
    public BaseDialog setAnimStyle(@StyleRes int animStyle){
        this.mAnimStyle = animStyle;
        return this;
    }

    /**
     * 设置点击dialog外部是否取消
     * @param outCancel true,click to dismiss
     * @return this dialog
     */
    public BaseDialog setOutCancel(boolean outCancel){
        this.mOutCancel = outCancel;
        return this;
    }


    /**
     * show dialog
     * @param manager fragment manager
     */
    public BaseDialog show(FragmentManager manager){
        super.show(manager, String.valueOf(System.currentTimeMillis()));
        return this;
    }
}
