package com.yufei.gadget.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.android.yufei.baselibrary.base.BaseFragment;
import com.yufei.gadget.R;
import com.yufei.module.android.AndroidFragment;
import com.yufei.module.framework.FrameWorkFragment;
import com.yufei.module.java.JavaFragment;
import com.yufei.module.mine.MineFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<BaseFragment> mFrameList;
    private FragmentManager fragmentManager;
    private AndroidFragment mAFragment;
    private JavaFragment mJFragment;
    private FrameWorkFragment mFWFragment;
    private MineFragment mMFragment;

    TextView tvHeader;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    tvHeader.setText("安卓");
                   selectFragment(mAFragment);
                    return true;
                case R.id.navigation_dashboard:
                    tvHeader.setText("Java");
                    selectFragment(mJFragment);
                    return true;
                case R.id.navigation_notifications:
                    tvHeader.setText("应用层");
                    selectFragment(mFWFragment);
                    return true;
                case R.id.navigation_mine:
                    tvHeader.setText("我的");
                    selectFragment(mMFragment);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
        initToolbar();
    }

    private void initView(){
        if (fragmentManager == null) {
            fragmentManager = getSupportFragmentManager();
        }

        mFrameList = new ArrayList<>();
        initFragment();

        BottomNavigationView navigation =  findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void initToolbar(){
        Toolbar  mToolbar = findViewById(com.yufei.module.java.R.id.tl_java);
        tvHeader = findViewById(com.yufei.module.java.R.id.tv_java_title);
        setSupportActionBar(mToolbar);
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        tvHeader.setText("安卓");
    }

    /**
     * <p>initFragment</p>
     * @Description 用add方法添加所有fragment
     */
    private void initFragment() {
        //如果把FragmentTransaction作为全局变量，多次commit会报java.lang.IllegalStateException:commit already called
        FragmentTransaction ft = fragmentManager.beginTransaction();

        mAFragment = new AndroidFragment();
        mJFragment = new JavaFragment();
        mFWFragment = new FrameWorkFragment();
        mMFragment = new MineFragment();
        mFrameList.add(mAFragment);
        mFrameList.add(mJFragment);
        mFrameList.add(mFWFragment);
        mFrameList.add(mMFragment);

        for(BaseFragment fragment:mFrameList){
            ft.add(R.id.message, fragment);
            ft.hide(fragment);
        }
        ft.show(mAFragment).commit();
    }

    /**
     * <p>hideAllFragment</p>
     * @Description 隐藏全部fragment
     */
    private void hideAllFragment(FragmentTransaction ft) {
        for(BaseFragment fragment:mFrameList){
            ft.hide(fragment);
        }
    }

    /**
     * <p>selectFragment</p>
     * @param fragment
     * @Description 显示被选中的fragment
     */
    private void selectFragment(BaseFragment fragment) {
        FragmentTransaction ft = fragmentManager.beginTransaction();
        hideAllFragment(ft);
        ft.show(fragment);
        ft.commit();
    }


    private void initData(){

    }

}
