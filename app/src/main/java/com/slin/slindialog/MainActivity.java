package com.slin.slindialog;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.slin.dialog.SlinDialog;
import com.slin.dialog.core.DialogViewHolder;
import com.slin.dialog.viewholder.ConfirmDialogViewHolder;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onConfirmDialogClick(View view) {
        ConfirmDialogViewHolder viewHolder = new ConfirmDialogViewHolder.Builder()
                .msg("这是消息")
                .title("标题")
                .confirmText("确认")
                .cancelText("取消")
                .icon(getDrawable(R.drawable.slin))
                .confirmListener(((view1, dialog) -> {
                    Toast.makeText(this, "确认", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }))
                .build();
        SlinDialog.showDialog(getSupportFragmentManager(), viewHolder);
    }

    public void onConfirmDialogClick2(View view) {
        DialogViewHolder viewHolder = new DialogViewHolder.Builder()
                .layoutResId(R.layout.dialog_confirm)
                .onViewConvertedListener((holder, dialog) ->
                        holder.setText(R.id.tv_dialog_title, "title")
                                .setText(R.id.tv_dialog_msg, "message")
                                .setText(R.id.btn_dialog_ok, "ok")
                                .setText(R.id.btn_dialog_cancel, "cancel")
                                .setOnClickListener(R.id.btn_dialog_cancel, v -> dialog.dismiss())
                                .setOnClickListener(R.id.btn_dialog_ok, v -> {
                                    Toast.makeText(this, "confirm", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                }))
                .build();
        SlinDialog.newInstance()
                .setDialogViewHolder(viewHolder)
                .show(getSupportFragmentManager());

    }

}
