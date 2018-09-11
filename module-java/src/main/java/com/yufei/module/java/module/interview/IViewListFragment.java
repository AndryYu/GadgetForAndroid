package com.yufei.module.java.module.interview;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.yufei.baselibrary.base.BWebViewActivity;
import com.android.yufei.baselibrary.base.BaseFragment;
import com.android.yufei.baselibrary.common.BConstants;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.yufei.module.java.R;

import java.util.ArrayList;
import java.util.List;

public class IViewListFragment extends BaseFragment {

    ListView mLView;
    List<String> mListData;
    SparseArray<String> mSArray;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_interview, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mLView = view.findViewById(R.id.lv_interview);

        initData();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,mListData);//新建并配置ArrayAapeter
        mLView.setAdapter(adapter);
        mLView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), BWebViewActivity.class);
                intent.putExtra(BConstants.URL_KEY_BASE, mSArray.get(i));
                startActivity(intent);
            }
        });
    }

    private void initData(){
        mListData = new ArrayList<>();
        mListData.add("1.synchronized与static synchronized 的区别");
        mListData.add("2.Java中一个类里面有两个用synchronized修饰的非静态方法，不同的线程中的实例访问这两个方法时会发生什么？");
        mListData.add("3.java中volatile、synchronized和lock解析");
        mListData.add("4.深入理解JVM之JVM内存区域与内存分配");
        mListData.add("5.List,Set,Map的区别");
        mListData.add("6.Java并发编程：深入剖析ThreadLocal");
        mSArray = new SparseArray<>();
        mSArray.put(0, "https://blog.csdn.net/wangtaomtk/article/details/52318634");
        mSArray.put(1, "https://blog.csdn.net/Sunjy1881/article/details/76269829");
        mSArray.put(2, "https://blog.csdn.net/ztchun/article/details/60778950");
        mSArray.put(3, "https://www.cnblogs.com/wangjzh/p/5258254.html");
        mSArray.put(4, "https://www.cnblogs.com/IvesHe/p/6108933.html");
        mSArray.put(5, "http://www.cnblogs.com/dolphin0520/p/3920407.html");
    }

    @Override
    public LifecycleTransformer bindToLife() {
        return null;
    }

}
