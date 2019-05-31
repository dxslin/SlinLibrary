package com.slin.dialog.viewholder;

import android.graphics.drawable.Drawable;
import android.view.View;

import com.slin.dialog.R;
import com.slin.dialog.core.BaseSlinDialog;
import com.slin.dialog.core.DialogViewHolder;
import com.slin.dialog.core.ViewHolder;

/**
 * author: slin
 * date: 2019-05-31
 * description:
 */
public class ConfirmDialogViewHolder extends DialogViewHolder {

    private String title = "Title";
    private String msg = "message";
    private Drawable icon;
    private String confirmText = "Ok";
    private String cancelText = "Cancel";
    private OnConfirmListener confirmListener;      //确认键事件
    private OnCancelListener cancelListener;        //取消键事件

    public ConfirmDialogViewHolder() {
        super(R.layout.dialog_confirm);
    }

    @Override
    public void onViewConverted(View view, ViewHolder helper) {
        helper.setText(R.id.tv_dialog_title, title)
                .setText(R.id.tv_dialog_msg, msg)
                .setText(R.id.btn_dialog_ok, confirmText)
                .setText(R.id.btn_dialog_cancel, cancelText)
                .setOnClickListener(R.id.btn_dialog_cancel, v -> {
                    mDialog.dismiss();
                    if (cancelListener != null) {
                        cancelListener.onCancel(v, mDialog);
                    }
                })
                .setOnClickListener(R.id.btn_dialog_ok, v -> {
                    if (confirmListener != null) {
                        confirmListener.onConfirm(v, mDialog);
                    }
                });
        if (icon != null) {
            helper.setDrawable(R.id.iv_dialog_icon, icon);
        }
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
    }

    public void setConfirmText(String confirmText) {
        this.confirmText = confirmText;
    }

    public void setCancelText(String cancelText) {
        this.cancelText = cancelText;
    }

    public void setConfirmListener(OnConfirmListener confirmListener) {
        this.confirmListener = confirmListener;
    }

    public void setCancelListener(OnCancelListener cancelListener) {
        this.cancelListener = cancelListener;
    }

    public interface OnCancelListener {
        void onCancel(View view, BaseSlinDialog dialog);
    }

    public interface OnConfirmListener {
        void onConfirm(View view, BaseSlinDialog dialog);
    }


    private ConfirmDialogViewHolder(Builder builder) {
        this();
        if (builder.title != null) {
            this.title = builder.title;
        }
        if (builder.msg != null) {
            this.msg = builder.msg;
        }
        if (builder.icon != null) {
            this.icon = builder.icon;
        }
        if (builder.confirmText != null) {
            this.confirmText = builder.confirmText;
        }
        if (builder.cancelText != null) {
            this.cancelText = builder.cancelText;
        }
        if (builder.cancelListener != null) {
            this.cancelListener = builder.cancelListener;
        }
        if (builder.confirmListener != null) {
            this.confirmListener = builder.confirmListener;
        }
    }

    public static final class Builder {
        private String title;
        private String msg;
        private Drawable icon;
        private String confirmText;
        private String cancelText;
        private OnConfirmListener confirmListener;
        private OnCancelListener cancelListener;

        public Builder() {
        }

        public Builder title(String val) {
            title = val;
            return this;
        }

        public Builder msg(String val) {
            msg = val;
            return this;
        }

        public Builder icon(Drawable val) {
            icon = val;
            return this;
        }

        public Builder confirmText(String val) {
            confirmText = val;
            return this;
        }

        public Builder cancelText(String val) {
            cancelText = val;
            return this;
        }

        public Builder confirmListener(OnConfirmListener val) {
            confirmListener = val;
            return this;
        }

        public Builder cancelListener(OnCancelListener val) {
            cancelListener = val;
            return this;
        }

        public ConfirmDialogViewHolder build() {
            return new ConfirmDialogViewHolder(this);
        }
    }
}
