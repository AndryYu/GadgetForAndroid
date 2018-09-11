package com.yufei.module.java;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.yufei.baselibrary.base.BaseFragment;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.yufei.module.java.common.JConstants;

import java.util.ArrayList;
import java.util.List;


public class JavaFragment extends BaseFragment {

    RecyclerView mRVGrid;
    private List<String> mGridList;
    TextView tvHeader;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_java, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRVGrid = view.findViewById(R.id.rv_grid);
        //设置布局的方式
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(),2);
        mRVGrid.setLayoutManager(layoutManager);

        initToolbar(view);
        initData();

        BaseQuickAdapter gridAdapter = new BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_java_grid, mGridList) {
            @Override
            protected void convert(BaseViewHolder helper, String item) {
                //调用赋值
                helper.setText(R.id.tv_item, item);
                helper.addOnClickListener(R.id.tv_item);
            }
        };
        gridAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getActivity(), JavaHomeActivity.class);
                intent.putExtra(JConstants.KEY_GRID_POS, position);
                startActivity(intent);
            }
        });
        mRVGrid.setAdapter(gridAdapter);
    }


    private void initToolbar(View view) {
        Toolbar mToolbar = view.findViewById(R.id.tl_java);
        tvHeader = view.findViewById(R.id.tv_java_title);
        ((AppCompatActivity)getActivity()).setSupportActionBar(mToolbar);
        ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        if(actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
        }
        tvHeader.setText("Java");
    }

    private void initData(){
        mGridList = new ArrayList<>();

        mGridList.add("Java基础");
        mGridList.add("Java进阶");
        mGridList.add("Java线程知识点");
        mGridList.add("Java虚拟机");
        mGridList.add("23种设计模式");
        mGridList.add("Java数据结构");
        mGridList.add("Java基本算法");
        mGridList.add("Java面试题");
    }

    @Override
    public LifecycleTransformer bindToLife() {
        return bindUntilEvent(FragmentEvent.DESTROY);
    }
}
