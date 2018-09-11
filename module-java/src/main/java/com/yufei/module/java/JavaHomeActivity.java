package com.yufei.module.java;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.yufei.baselibrary.base.BaseActivity;
import com.android.yufei.baselibrary.base.BaseFragment;
import com.yufei.module.java.common.JConstants;
import com.yufei.module.java.module.algorithm.AlgoListFragment;
import com.yufei.module.java.module.interview.IViewListFragment;

public class JavaHomeActivity extends BaseActivity {

    int position;
    TextView tvHeader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_algo);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }

        position = getIntent().getIntExtra(JConstants.KEY_GRID_POS, 0);
        initView();
        initFragment();
    }

    private void initView(){
        Toolbar  mToolbar = findViewById(R.id.tl_java);
        tvHeader = findViewById(R.id.tv_java_title);
        setSupportActionBar(mToolbar);
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        tvHeader.setText("算法类型");
    }

    private void initFragment(){
        BaseFragment showFragment = new AlgoListFragment();
        switch (position){
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                showFragment = new AlgoListFragment();
                break;
            case 7:
                showFragment = new IViewListFragment();
                tvHeader.setText("Java面试题");
             break;

        }
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_alg, showFragment).commit();
    }
}
