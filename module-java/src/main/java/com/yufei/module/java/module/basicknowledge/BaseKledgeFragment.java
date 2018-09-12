package com.yufei.module.java.module.basicknowledge;

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

public class BaseKledgeFragment extends BaseFragment {

    ListView mLView;
    List<String> mListData;
    SparseArray<String> mSArray;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_java_base, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mLView = view.findViewById(R.id.lv_base_knowledge);

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
        mListData.add("1.String为什么不可变?");
        mListData.add("2.十大排序原理");

        mSArray = new SparseArray<>();
        mSArray.put(0, "https://blog.csdn.net/zhangjg_blog/article/details/18319521");
        mSArray.put(1, "https://blog.csdn.net/qq_34374664/article/details/79545940");
    }

    @Override
    public LifecycleTransformer bindToLife() {
        return null;
    }
}
