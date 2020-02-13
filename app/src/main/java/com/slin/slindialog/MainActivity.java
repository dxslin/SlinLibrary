package com.slin.slindialog;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.slin.dialog.SlinDialog;
import com.slin.dialog.core.DialogViewHolder;
import com.slin.dialog.viewholder.ConfirmDialogViewHolder;
import com.slin.indicator.ViewPagerIndicator;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    String title[] = {"销售", "php", "汽车电子开发工程师", "Android", "汽车项目管理", "厂长", "建筑设计师", "服务员"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPagerIndicator vpi_advertisingJob = findViewById(R.id.vpi_advertisingJob);
        ViewPager vp_resumeListPage = findViewById(R.id.vp_resumeListPage);
        vpi_advertisingJob.setTabTexts(title);
        vpi_advertisingJob.bindViewPager(vp_resumeListPage);

        List<Fragment> fragments = new ArrayList<>(title.length);
        for (int i = 0; i < title.length; i++) {
            fragments.add(BlankFragment.newInstance(title[i]));
        }

        vp_resumeListPage.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager(),
                FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });


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
