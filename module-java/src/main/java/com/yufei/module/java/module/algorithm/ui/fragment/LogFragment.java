package com.yufei.module.java.module.algorithm.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.yufei.baselibrary.base.BaseFragment;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.yufei.module.java.R;
import com.yufei.module.java.module.algorithm.ui.adapter.LogAdapter;

import java.util.ArrayList;

public class LogFragment extends BaseFragment {

    LogAdapter adapter;
    RecyclerView recyclerView;
    View emptyView;

    public static LogFragment newInstance() {
        LogFragment fragment = new LogFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_log, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView =  view.findViewById(R.id.rv_log);
        emptyView = view.findViewById(R.id.empty_view);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(llm);

        adapter = new LogAdapter(new ArrayList<String>());
        recyclerView.setAdapter(adapter);
    }

    public void addLog(final String log) {
        emptyView.setVisibility(View.GONE);
        adapter.addLog(log,recyclerView);
    }

    public void clearLog() {
        if (adapter != null)
            adapter.clearLog();
        if (emptyView != null)
            emptyView.setVisibility(View.VISIBLE);
    }

    @Override
    public LifecycleTransformer bindToLife() {
        return null;
    }
}
