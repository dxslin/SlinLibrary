package com.slin.slindialog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.slin.dialog.ConfirmDialogViewHolder;
import com.slin.dialog.DialogHelper;

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
        DialogHelper.showDialog(getSupportFragmentManager(), viewHolder);
    }

}
