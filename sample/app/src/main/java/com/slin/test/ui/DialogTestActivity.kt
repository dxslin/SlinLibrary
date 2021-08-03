package com.slin.test.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.slin.core.ui.CoreActivity
import com.slin.dialog.SlinDialog
import com.slin.dialog.core.BaseSlinDialog
import com.slin.dialog.core.DialogViewHolder
import com.slin.dialog.core.ViewHolder
import com.slin.dialog.viewholder.ConfirmDialogViewHolder
import com.slin.test.R
import com.slin.test.databinding.ActivityDialogTestBinding
import com.slin.viewbinding.viewBinding

class DialogTestActivity : CoreActivity() {

    private val binding: ActivityDialogTestBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.btnConfirmDialog
    }

    fun onConfirmDialogClick(view: View) {
        val viewHolder = ConfirmDialogViewHolder.Builder()
                .msg("这是消息")
                .title("标题")
                .confirmText("确认")
                .cancelText("取消")
                .icon(getDrawable(R.drawable.slin))
                .confirmListener { _: View?, dialog: BaseSlinDialog ->
                    Toast.makeText(this, "确认", Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                }
                .build()
        SlinDialog.showDialog(supportFragmentManager, viewHolder)
    }

    fun onConfirmDialogClick2(view: View) {
        val viewHolder = DialogViewHolder.Builder()
                .layoutResId(R.layout.dialog_confirm)
                .onViewConvertedListener { holder: ViewHolder, dialog: BaseSlinDialog ->
                    holder.setText(R.id.tv_dialog_title, "title")
                            .setText(R.id.tv_dialog_msg, "message")
                            .setText(R.id.btn_dialog_ok, "ok")
                            .setText(R.id.btn_dialog_cancel, "cancel")
                            .setOnClickListener(R.id.btn_dialog_cancel) { dialog.dismiss() }
                            .setOnClickListener(R.id.btn_dialog_ok) {
                                Toast.makeText(this, "confirm", Toast.LENGTH_SHORT).show()
                                dialog.dismiss()
                            }
                }
                .build()
        SlinDialog.newInstance()
                .setDialogViewHolder(viewHolder)
                .show(supportFragmentManager)
    }

}
