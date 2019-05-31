package com.slin.slindialog;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.slin.dialog.SlinDialog;
import com.slin.dialog.viewholder.ConfirmDialogViewHolder;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onConfirmDialogClick(View view){
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

}
