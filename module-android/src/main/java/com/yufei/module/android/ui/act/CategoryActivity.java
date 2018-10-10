package com.yufei.module.android.ui.act;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.yufei.baselibrary.base.BaseActivity;
import com.android.yufei.baselibrary.utils.AssetUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.gson.Gson;
import com.yufei.module.android.R;
import com.yufei.module.android.common.AConstants;
import com.yufei.module.android.entity.CategoryEntity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class CategoryActivity extends BaseActivity {

    RecyclerView mRecyclerView;
    List<CategoryEntity.Category>  mList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        initViews();
    }

    private void initViews(){
        mRecyclerView = findViewById(R.id.rv_category);

        initRVData();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(CategoryActivity.this, LinearLayoutManager.VERTICAL, false));
        BaseQuickAdapter adapter = new BaseQuickAdapter<CategoryEntity.Category, BaseViewHolder>(R.layout.layout_item_category, mList) {
            @Override
            protected void convert(BaseViewHolder helper, final CategoryEntity.Category item) {
                //调用赋值
                helper.setText(R.id.tv_category_title, item.getCategory());
                View view = helper.itemView;
                RecyclerView subRecyclerView = view.findViewById(R.id.rv_sublevel);

                GridLayoutManager layoutManager = new GridLayoutManager(CategoryActivity.this,4);
                subRecyclerView.setLayoutManager(layoutManager);
                BaseQuickAdapter suAdapter = new BaseQuickAdapter<CategoryEntity.Category.Sublevel, BaseViewHolder>(R.layout.item_java_grid, item.getContent()) {
                    @Override
                    protected void convert(BaseViewHolder helper, CategoryEntity.Category.Sublevel item) {
                        //调用赋值
                        helper.setText(R.id.tv_item, item.getSublevel());
                        helper.addOnClickListener(R.id.tv_item);
                    }
                };
                suAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
                    @Override
                    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                        Intent intent = new Intent(CategoryActivity.this, ModuleListActivity.class);
                        CategoryEntity.Category.Sublevel sublevel = item.getContent().get(position);
                        intent.putExtra(AConstants.KEY_MODULE_CONTENT, sublevel);
                        startActivity(intent);
                    }
                });
                subRecyclerView.setAdapter(suAdapter);
            }
        };
        mRecyclerView.setAdapter(adapter);
    }

    private void initRVData(){
        String category = AssetUtils.getJson(CategoryActivity.this, "category.json");
        Gson gson = new Gson();
        CategoryEntity entity = gson.fromJson(category, CategoryEntity.class);
        mList = entity.getData();
    }


}
