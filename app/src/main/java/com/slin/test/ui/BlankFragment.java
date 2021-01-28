package com.slin.test.ui;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.slin.test.R;
import com.slin.test.base.BaseFragment;
import com.slin.test.databinding.FragmentBlankBinding;

/**
 * Created by slin on 2020/2/13
 */
public class BlankFragment extends BaseFragment<FragmentBlankBinding> {

    private static final String ARG_HELLO_TEXT = "param1";

    private String mHelloText;

    public BlankFragment() {
        super(R.layout.fragment_blank);
    }

    public static BlankFragment newInstance(String param1) {
        BlankFragment fragment = new BlankFragment();
        Bundle args = new Bundle();
        args.putString(ARG_HELLO_TEXT, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mHelloText = getArguments().getString(ARG_HELLO_TEXT);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getBinding().tvHelloText.setText(mHelloText);

    }
}
