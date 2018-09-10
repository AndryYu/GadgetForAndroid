package com.yufei.module.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.android.yufei.baselibrary.base.BaseFragment;
import com.android.yufei.baselibrary.base.OnItemClickListener;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.yufei.module.android.common.AConstants;
import com.yufei.module.android.ui.adapter.MyAdapter;

import java.util.ArrayList;
import java.util.List;

public class AndroidFragment extends BaseFragment {

    RecyclerView rvGrid;
    RecyclerView rvList;
    MyAdapter mAdapter;
    List<String> mList;
    SparseArray<String> mSArray;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_android, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvGrid = view.findViewById(R.id.rv_grid);
        rvList = view.findViewById(R.id.rv_list);

        initRVData();
        initRecyclerView();
    }

    private void initRVData(){
        mList = new ArrayList<>();
        mList.add("1.RecyclerView使用总结");
        mList.add("2.RecyclerView与ListView的比较");
        mSArray = new SparseArray();
        mSArray.put(0, "https://www.jianshu.com/p/4f9591291365");
        mSArray.put(1, "https://blog.csdn.net/shu_lance/article/details/79566189");
    }

    private void initRecyclerView(){
        //设置RecyclerView管理器
        rvList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        //初始化适配器
        mAdapter = new MyAdapter(mList);
        //设置添加或删除item时的动画，这里使用默认动画
        rvList.setItemAnimator(new DefaultItemAnimator());
        //设置适配器
        rvList.setAdapter(mAdapter);
        mAdapter.setItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int postion) {
                Intent intent = new Intent(getActivity(), AndroidActivity.class);
                String url = mSArray.get(postion);
                intent.putExtra(AConstants.URL_KEY,url);
                startActivity(intent);
            }
        });
    }

    @Override
    public LifecycleTransformer bindToLife() {
        return bindUntilEvent(FragmentEvent.DESTROY);
    }
}
