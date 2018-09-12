package com.yufei.module.java.module.algorithm;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import com.android.yufei.baselibrary.base.BaseFragment;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.yufei.module.java.R;
import com.yufei.module.java.module.algorithm.entity.Algorithm;
import com.yufei.module.java.module.algorithm.entity.graph.GraphTraversalAlgorithm;
import com.yufei.module.java.module.algorithm.entity.tree.bst.BSTAlgorithm;
import com.yufei.module.java.module.algorithm.ui.VisualActivity;
import com.yufei.module.java.module.algorithm.ui.adapter.ExListAdapter;
import com.yufei.module.java.module.algorithm.ui.adapter.ExMenuModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AlgoListFragment extends BaseFragment {

    ExpandableListAdapter exListAdapter;
    ExpandableListView exListView;
    List<ExMenuModel> mModelList;
    HashMap<ExMenuModel, List<String>> mModelMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        exListView = view.findViewById(R.id.elv_alg);

        prepareListData();
        exListAdapter = new ExListAdapter(getActivity(), mModelList, mModelMap, exListView);
        exListView.setAdapter(exListAdapter);
        initListener();
    }

    /**
     * <p>prepareListData</p>
     * @Description  准备数据
     */
    private void prepareListData() {
        mModelList = new ArrayList<ExMenuModel>();
        mModelMap = new HashMap<ExMenuModel, List<String>>();

        ExMenuModel item1 = new ExMenuModel();
        item1.setName("查找算法（Search）");
        mModelList.add(item1);

        ExMenuModel item2 = new ExMenuModel();
        item2.setName("排序算法（Sorting）");
        mModelList.add(item2);

        ExMenuModel item3 = new ExMenuModel();
        item3.setName("树（Tree）");
        mModelList.add(item3);

        ExMenuModel item4 = new ExMenuModel();
        item4.setName("列表（List）");
        mModelList.add(item4);

        ExMenuModel item5 = new ExMenuModel();
        item5.setName("图（Graph）");
        mModelList.add(item5);

        ExMenuModel item6 = new ExMenuModel();
        item6.setName("回溯算法（Backtracking）");
        mModelList.add(item6);


        List<String> heading1 = new ArrayList<>();
        heading1.add("1.二分查找（Binary search）");
        heading1.add("2.线性查找（Linear Search）");

        List<String> heading2 = new ArrayList<String>();
        heading2.add("1.冒泡排序（Bubble Sort）");
        heading2.add("2.插入排序（Insertion Sort）");
        heading2.add("3.选择排序（Selection Sort）");
        heading2.add("4.快速排序（Quicksort）");

        List<String> heading3 = new ArrayList<String>();
        heading3.add("1.二叉搜索树（BST Search）");
        heading3.add("2.二叉插入树（BST Insert）");

        List<String> heading4 = new ArrayList<String>();
        heading4.add("1.Linked List");
        heading4.add("2.堆（Stack）");

        List<String> heading5 = new ArrayList<String>();
        heading5.add("1.广度优先遍历（BFS Traversal）");
        heading5.add("2.深度优先遍历（DFS Travsersal）");
        heading5.add("3.最短路径法（Dijkstra）");
        heading5.add("4.Bellman Ford");

        List<String> heading6 = new ArrayList<String>();
        heading6.add("N Queens Problem");


        mModelMap.put(mModelList.get(0), heading1);
        mModelMap.put(mModelList.get(1), heading2);
        mModelMap.put(mModelList.get(2), heading3);
        mModelMap.put(mModelList.get(3), heading4);
        mModelMap.put(mModelList.get(4), heading5);
        mModelMap.put(mModelList.get(5), heading6);
    }

    private void  initListener(){
        exListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int groupPosition, int childPosition, long l) {
                switch (groupPosition) {
                    case 0:
                        switch (childPosition) {
                            case 0:
                                gotoVisualActivity(Algorithm.BINARY_SEARCH);
                                break;
                            case 1:
                                gotoVisualActivity(Algorithm.LINEAR_SEARCH);
                                break;
                        }
                        break;
                    case 1:
                        switch (childPosition) {
                            case 0:
                                gotoVisualActivity(Algorithm.BUBBLE_SORT);
                                break;
                            case 1:
                                gotoVisualActivity(Algorithm.INSERTION_SORT);
                                break;
                            case 2:
                                gotoVisualActivity(Algorithm.SELECTION_SORT);
                                break;
                            case 3:
                                gotoVisualActivity(Algorithm.QUICKSORT);
                                break;
                        }
                        break;
                    case 2:
                        switch (childPosition) {
                            case 0:
                                //vFragment.setStartCommand(BSTAlgorithm.START_BST_SEARCH);
                                gotoVisualActivity(Algorithm.BST_SEARCH, BSTAlgorithm.START_BST_SEARCH);
                                break;
                            case 1:
                                //vFragment.setStartCommand(BSTAlgorithm.START_BST_INSERT);
                                gotoVisualActivity(Algorithm.BST_INSERT, BSTAlgorithm.START_BST_INSERT);
                                break;
                        }
                        break;
                    case 3:
                        switch (childPosition) {
                            case 0:
                                gotoVisualActivity(Algorithm.LINKED_LIST);
                                break;
                            case 1:
                                gotoVisualActivity(Algorithm.STACK);
                                break;
                        }
                        break;
                    case 4:
                        switch (childPosition) {
                            case 0:
                               // vFragment.setStartCommand(GraphTraversalAlgorithm.TRAVERSE_BFS);
                                gotoVisualActivity(Algorithm.BFS, GraphTraversalAlgorithm.TRAVERSE_BFS);
                                break;
                            case 1:
                                //vFragment.setStartCommand(GraphTraversalAlgorithm.TRAVERSE_DFS);
                                gotoVisualActivity(Algorithm.DFS, GraphTraversalAlgorithm.TRAVERSE_DFS );
                                break;
                            case 2:
                                gotoVisualActivity(Algorithm.DIJKSTRA);
                                break;
                            case 3:
                                gotoVisualActivity(Algorithm.BELLMAN_FORD);
                                break;
                        }
                        break;
                    case 5:
                        switch (childPosition) {
                            case 0:
                                gotoVisualActivity(Algorithm.N_QUEENS);
                                break;
                        }
                        break;
                }
                return true;
            }
        });
        exListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                return false;
            }
        });
    }

    public void gotoVisualActivity(String code){
        gotoVisualActivity(code, "");
    }

    public void gotoVisualActivity(String code, String startCommand){
        Intent intent = new Intent(getActivity(), VisualActivity.class);
        intent.putExtra(Algorithm.KEY_ALGORITHM, code);
        intent.putExtra(Algorithm.KEY_STARTCOMMAND, startCommand);
        startActivity(intent);
    }

    @Override
    public LifecycleTransformer bindToLife() {
        return null;
    }
}
