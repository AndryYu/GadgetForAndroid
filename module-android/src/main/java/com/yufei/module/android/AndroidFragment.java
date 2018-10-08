package com.yufei.module.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.yufei.baselibrary.base.BaseFragment;
import com.android.yufei.baselibrary.base.OnItemClickListener;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.yufei.module.android.common.AConstants;
import com.yufei.module.android.ui.act.CategoryActivity;
import com.yufei.module.android.ui.act.ModuleListActivity;
import com.yufei.module.android.ui.adapter.MyAdapter;
import com.yufei.module.android.utils.GlideImageLoader;

import java.util.ArrayList;
import java.util.List;

public class AndroidFragment extends BaseFragment {

    RecyclerView rvGrid;
    RecyclerView rvList;
    MyAdapter mAdapter;
    List<String> mList;
    SparseArray<String> mSArray;

    private List<String> mGridList;

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

        initBanner(view);
        initGridRecyclerView();
        initRVData();
        initRecyclerView();
    }

    private void initBanner(View view){
        Banner banner =  view.findViewById(R.id.banner);
        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        List<Integer> images = new ArrayList<>();
        images.add(R.drawable.banner1);
        images.add(R.drawable.banner2);
        images.add(R.drawable.banner3);
        images.add(R.drawable.banner4);
        //设置图片集合
        banner.setImages(images);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.Default);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(3000);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }

    private void initGridData(){
        mGridList = new ArrayList<>();

        mGridList.add("Android基础知识梳理");
        mGridList.add("RxJava2 实战知识梳理");
        mGridList.add("图片压缩知识梳理");
        mGridList.add("性能优化工具知识梳理");
        mGridList.add("MD控件知识梳理");
        mGridList.add("插件化知识梳理");
        mGridList.add("View 绘制体系知识梳理");
        mGridList.add("其他更多");
    }

    private void initGridRecyclerView(){
        initGridData();
        //设置布局的方式
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(),4);
        rvGrid.setLayoutManager(layoutManager);
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
                Intent intent = null;
                if(position!= mGridList.size()-1) {
                    intent = new Intent(getActivity(), ModuleListActivity.class);
                    intent.putExtra(AConstants.KEY_MODULE_TITLE, mGridList.get(position));
                }else{
                    intent = new Intent(getActivity(), CategoryActivity.class);
                }
                startActivity(intent);
            }
        });
        rvGrid.setAdapter(gridAdapter);
    }

    private void initRVData(){
        mList = new ArrayList<>();
        mList.add("1.RecyclerView使用总结");
        mList.add("2.RecyclerView与ListView的比较");
        mList.add("3.Android WebView 全面干货指南");
        mList.add("4.说一说AsyncTask的内部实现原理？");
        mList.add("5.怎么定义一个Behavior吗？");
        mList.add("6.RecyclerView的拖拽怎么实现的？");
        mList.add("7.service两种启动方式有什么区别？");
        mList.add("8.Android EventBus 的源码解析");
        mList.add("9.Android中常见的内存泄漏和解决方案");
        mList.add("10.Android内存优化之OOM");
        mList.add("11.最新2017（Android）安卓面试题级答案（精选版）");
        mList.add("12.2018年Android面试题含答案--适合中高级（下）");
        mList.add("13.Android的消息机制之ThreadLocal的工作原理");
        mList.add("14.Android View的绘制流程");
        mList.add("15.Android 自定义View、ViewGroup和自定义属性");
        mList.add("16.SQLite入门知识总结");
        mList.add("17.多线程总结");
        mList.add("18.2018年Android面试题含答案--适合中高级（上）");
        mList.add("19.SharedPreference源码分析");
        mList.add("20.Glide-源码详解");
        mList.add("21.WebView性能、体验分析与优化");
        mList.add("22.Android WebView性能优化");
        mList.add("23.23种设计模式全解析");
        mList.add("24.深入理解Java类加载器(ClassLoader)");
        mList.add("25.跟着例子一步步学习redux+react-redux");
        mList.add("26.八大排序算法总结与java实现");
        mList.add("27.性能优化工具知识梳理");
        mList.add("28.一个大神的简书文章目录");
        mList.add("29.即时通讯（IM）面试题");
        mList.add("30.RxJava2 实战知识梳理");
        mList.add("31.应用保活终极总结");

        mSArray = new SparseArray<>();
        mSArray.put(0, "https://www.jianshu.com/p/4f9591291365");
        mSArray.put(1, "https://blog.csdn.net/shu_lance/article/details/79566189");
        mSArray.put(2, "https://www.jianshu.com/p/fd61e8f4049e");
        mSArray.put(3, "https://www.cnblogs.com/absfree/p/5357678.html");
        mSArray.put(4, "https://www.jianshu.com/p/82d18b0d18f4");
        mSArray.put(5, "https://blog.csdn.net/aiynmimi/article/details/77744610");
        mSArray.put(6, "https://blog.csdn.net/siwen1234/article/details/50292683");
        mSArray.put(7, "https://mp.weixin.qq.com/s/pZAbNe1pE1_JUUv4j9KAow");
        mSArray.put(8, "http://www.cocoachina.com/android/20180207/22193.html");
        mSArray.put(9, "http://hukai.me/android-performance-oom/");
        mSArray.put(10, "https://mp.weixin.qq.com/s?__biz=MzI3OTU0MzI4MQ==&mid=2247484263&idx=1&sn=6815372a21f594a417b2185dd06e1cd3&chksm=eb476ff9dc30e6efebd6434cd259ccf2559779e360c001d253d6e12d48e4f6ec0ecf31d17cd0&scene=21#wechat_redirect");
        mSArray.put(11, "https://mp.weixin.qq.com/s?__biz=MzI3OTU0MzI4MQ==&mid=2247486052&idx=1&sn=108a2d8d4061ca2058ea8342a13721c6&chksm=eb4766fadc30efecd1193dec639b06dbb94b6587065d34dde89642f4430bb19ddfa3dcb5f611&scene=21#wechat_redirect");
        mSArray.put(12, "https://blog.csdn.net/singwhatiwanna/article/details/48350919");
        mSArray.put(13, "https://www.jianshu.com/p/5a71014e7b1b");
        mSArray.put(14, "https://www.cnblogs.com/xiakexinghouzi/p/7843409.html");
        mSArray.put(15, "https://blog.csdn.net/lly347705530/article/details/79125373");
        mSArray.put(16, "https://blog.csdn.net/javazejian/article/details/50878665");
        mSArray.put(17, "https://www.cnblogs.com/huangjialin/p/8657565.html");
        mSArray.put(18, "https://www.jianshu.com/p/1be4eb02f6a8");
        mSArray.put(19, "https://blog.csdn.net/yulyu/article/details/60331803");
        mSArray.put(20, "https://mp.weixin.qq.com/s/-WceVvEKp8bKtIJQsD3Srw");
        mSArray.put(21, "https://www.jianshu.com/p/c13eb7759c68");
        mSArray.put(22, "https://www.cnblogs.com/geek6/p/3951677.html");
        mSArray.put(23, "https://blog.csdn.net/javazejian/article/details/73413292");
        mSArray.put(24, "https://segmentfault.com/a/1190000012976767");
        mSArray.put(25, "https://blog.csdn.net/u010983881/article/details/76383527");
        mSArray.put(26, "https://www.jianshu.com/p/37c263f9886b");
        mSArray.put(27, "https://www.jianshu.com/u/37baa8a86582");
        mSArray.put(28, "https://blog.csdn.net/flower_55/article/details/51565378");
        mSArray.put(29, "https://www.jianshu.com/p/c935d0860186");
        mSArray.put(30, "http://www.52im.net/thread-1135-1-1.html");

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
