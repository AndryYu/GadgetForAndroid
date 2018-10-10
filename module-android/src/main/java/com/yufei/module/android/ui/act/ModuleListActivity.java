package com.yufei.module.android.ui.act;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;

import com.android.yufei.baselibrary.base.BaseActivity;
import com.android.yufei.baselibrary.base.OnItemClickListener;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yufei.module.android.AndroidActivity;
import com.yufei.module.android.R;
import com.yufei.module.android.common.AConstants;
import com.yufei.module.android.entity.CategoryEntity;
import com.yufei.module.android.entity.DetailEntity;
import com.yufei.module.android.ui.adapter.MyAdapter;

import java.util.ArrayList;
import java.util.List;

public class ModuleListActivity extends BaseActivity {

    TextView tvTitle;
    String titleName;
    CategoryEntity.Category.Sublevel mSublevel;
    List<String> mList;
    SparseArray<String> mSArray;
    RecyclerView rvList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module_list);
        rvList = findViewById(R.id.rv_module_list);
        mSublevel = (CategoryEntity.Category.Sublevel) getIntent().getSerializableExtra(AConstants.KEY_MODULE_CONTENT);
        titleName = mSublevel.getSublevel();
        initToolbar();
        initRVData();
        initRecyclerView();
    }

    private void initToolbar() {
        Toolbar mToolbar = findViewById(R.id.tb_module_list);
        tvTitle = findViewById(R.id.tv_module_title);
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
        }
        tvTitle.setText(titleName);
    }

    private void initRVData(){
        mList = new ArrayList<>();
        mList.add("Java&Android 基础知识梳理(1) - 注解");
        mList.add("Java&Android 基础知识梳理(2) - 序列化");
        mList.add("Java&Android 基础知识梳理(3) - 内存区域");
        mList.add("Java&Android 基础知识梳理(4) - 垃圾收集器与内存分配策略");
        mList.add("Java&Android 基础知识梳理(5) - 类加载&对象实例化");
        mList.add("Java&Android 基础知识梳理(6) - 字节输入输出流");
        mList.add("Java&Android 基础知识梳理(7) - Android 虚拟机");
        mList.add("Java&Android 基础知识梳理(8) - 容器类");
        mList.add("Java&Android 基础知识梳理(9) - LruCache 源码解析");
        mList.add("Java&Android 基础知识梳理(10) - SparseArray 源码解析");
        mList.add("Java&Android 基础知识梳理(11) - 浅拷贝 Vs 深拷贝");

        mSArray = new SparseArray<>();
        mSArray.put(0, "https://www.jianshu.com/p/2585d2a7cd97");
        mSArray.put(1, "https://www.jianshu.com/p/4cda60d02c42");
        mSArray.put(2, "https://www.jianshu.com/p/bc530b038171");
        mSArray.put(3, "https://www.jianshu.com/p/3fa3172cf9e1");
        mSArray.put(4, "https://www.jianshu.com/p/e1e00bbf29dc");
        mSArray.put(5, "https://www.jianshu.com/p/3662fab6eb1e");
        mSArray.put(6, "https://www.jianshu.com/p/6224353197fd");
        mSArray.put(7, "https://www.jianshu.com/p/a8d5d38c6716");
        mSArray.put(8, "https://www.jianshu.com/p/7d0522e97fc2");
        mSArray.put(9, "https://www.jianshu.com/p/b774600904d4");
        mSArray.put(10, "https://www.jianshu.com/p/316a2a788007");
    }

    private void initRecyclerView(){
        //设置RecyclerView管理器
        rvList.setLayoutManager(new LinearLayoutManager(ModuleListActivity.this, LinearLayoutManager.VERTICAL, false));
        //设置添加或删除item时的动画，这里使用默认动画
        rvList.setItemAnimator(new DefaultItemAnimator());

        BaseQuickAdapter mAdapter = new BaseQuickAdapter<DetailEntity, BaseViewHolder>(R.layout.item_java_grid, mSublevel.getData()) {
            @Override
            protected void convert(BaseViewHolder helper, DetailEntity item) {
                //调用赋值
                helper.setText(R.id.tv_item, item.getTitle());
                helper.addOnClickListener(R.id.tv_item);
            }
        };

        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(ModuleListActivity.this, AndroidActivity.class);
                String url = mSublevel.getData().get(position).getUrl();
                intent.putExtra(AConstants.URL_KEY,url);
                startActivity(intent);
            }
        });
        //设置适配器
        rvList.setAdapter(mAdapter);
    }
}
