package com.yufei.module.android;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.android.yufei.baselibrary.base.BaseActivity;
import com.yufei.module.android.common.AConstants;

public class AndroidActivity extends BaseActivity {

    WebSettings webSettings;
    WebView mWebView;
    String url;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android);

        url = getIntent().getStringExtra(AConstants.URL_KEY);
        initWebView();
        initToolBar();
    }

    private void initWebView(){
        mWebView = findViewById(R.id.wv_home_android);
        webSettings = mWebView.getSettings();
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);//设置js可以直接打开窗口，如window.open()，默认为false
        webSettings.setJavaScriptEnabled(true);//是否允许执行js，默认为false。设置true时，会提醒可能造成XSS漏洞
        webSettings.setSupportZoom(true);//是否可以缩放，默认true
        webSettings.setBuiltInZoomControls(true);//是否显示缩放按钮，默认false
        webSettings.setUseWideViewPort(true);//设置此属性，可任意比例缩放。大视图模式
        webSettings.setLoadWithOverviewMode(true);//和setUseWideViewPort(true)一起解决网页自适应问题
        webSettings.setAppCacheEnabled(true);//是否使用缓存
        webSettings.setDomStorageEnabled(true);//DOM Storage
        mWebView.loadUrl(url);
        //该方法解决的问题是打开浏览器不调用系统浏览器，直接用webview打开
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }

    private  void initToolBar(){
        Toolbar mToolbar = findViewById(R.id.tl_java);
        TextView tvHeader = findViewById(R.id.tv_java_title);
        setSupportActionBar(mToolbar);
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        tvHeader.setText("详情界面");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mWebView.destroy();
    }
}
