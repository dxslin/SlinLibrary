package com.slin.dialog;

import androidx.fragment.app.FragmentManager;

import com.slin.dialog.core.BaseSlinDialog;
import com.slin.dialog.core.DialogViewHolder;

/**
 * author: slin
 * date: 2019-05-30
 * description:
 */
public class SlinDialog {

    public static BaseSlinDialog newInstance(DialogViewHolder viewHolder) {
        return BaseSlinDialog.newInstance()
                .setDialogViewHolder(viewHolder);
    }

    public static BaseSlinDialog newInstance() {
        return BaseSlinDialog.newInstance();
    }

    public static void showDialog(FragmentManager manager, DialogViewHolder viewHolder) {
        BaseSlinDialog.newInstance()
                .setDialogViewHolder(viewHolder)
                .setOutCancel(false)
                .show(manager);
    }

}
