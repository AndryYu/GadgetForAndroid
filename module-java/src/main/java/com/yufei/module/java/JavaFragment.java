package com.yufei.module.java;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.yufei.baselibrary.base.BaseFragment;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.android.FragmentEvent;


public class JavaFragment extends BaseFragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_java, container, false);
    }


    @Override
    public LifecycleTransformer bindToLife() {
        return bindUntilEvent(FragmentEvent.DESTROY);
    }
}
