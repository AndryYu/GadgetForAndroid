package com.yufei.module.java;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.yufei.baselibrary.base.BaseActivity;
import com.yufei.module.java.module.algorithm.AlgoListFragment;

public class JavaHomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_algo);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }

        initView();
        initData();
    }

    private void initView(){
        Toolbar  mToolbar = findViewById(R.id.tl_java);
        TextView tvHeader = findViewById(R.id.tv_java_title);
        setSupportActionBar(mToolbar);
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        tvHeader.setText("算法类型");

    }

    private void initData(){
        AlgoListFragment algFragment = new AlgoListFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_alg, algFragment).commit();
    }
}
