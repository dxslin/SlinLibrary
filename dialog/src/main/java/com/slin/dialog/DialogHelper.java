package com.slin.dialog;

import androidx.fragment.app.FragmentManager;

/**
 * author: slin
 * date: 2019-05-30
 * description:
 */
public class DialogHelper {

    public static BaseDialog showDialog(FragmentManager manager, DialogViewHolder viewHolder) {
        return BaseDialog.newInstance()
                .setDialogViewHolder(viewHolder)
                .setOutCancel(false)
                .show(manager);
    }

}
