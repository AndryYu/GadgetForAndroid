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
