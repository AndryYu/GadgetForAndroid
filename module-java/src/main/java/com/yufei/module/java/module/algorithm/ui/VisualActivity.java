package com.yufei.module.java.module.algorithm.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.android.yufei.baselibrary.base.BaseActivity;
import com.android.yufei.baselibrary.base.BaseFragment;
import com.yufei.module.java.R;
import com.yufei.module.java.module.algorithm.AlgoCompletionListener;
import com.yufei.module.java.module.algorithm.DataUtils;
import com.yufei.module.java.module.algorithm.entity.Algorithm;
import com.yufei.module.java.module.algorithm.entity.graph.BellmanFordAlgorithm;
import com.yufei.module.java.module.algorithm.entity.graph.DijkstraAgorithm;
import com.yufei.module.java.module.algorithm.entity.graph.GraphTraversalAlgorithm;
import com.yufei.module.java.module.algorithm.entity.list.LinkedList;
import com.yufei.module.java.module.algorithm.entity.list.Stack;
import com.yufei.module.java.module.algorithm.entity.search.BinarySearch;
import com.yufei.module.java.module.algorithm.entity.search.LinearSearch;
import com.yufei.module.java.module.algorithm.entity.sorting.BubbleSort;
import com.yufei.module.java.module.algorithm.entity.sorting.InsertionSort;
import com.yufei.module.java.module.algorithm.entity.sorting.QuickSort;
import com.yufei.module.java.module.algorithm.entity.sorting.SelectionSort;
import com.yufei.module.java.module.algorithm.entity.tree.bst.BSTAlgorithm;
import com.yufei.module.java.module.algorithm.ui.adapter.VPFragmentAdapter;
import com.yufei.module.java.module.algorithm.ui.fragment.CodeFragment;
import com.yufei.module.java.module.algorithm.ui.fragment.DescrFragment;
import com.yufei.module.java.module.algorithm.ui.fragment.LogFragment;
import com.yufei.module.java.widget.visualizer.AlgorithmVisualizer;
import com.yufei.module.java.widget.visualizer.ArrayVisualizer;
import com.yufei.module.java.widget.visualizer.BSTVisualizer;
import com.yufei.module.java.widget.visualizer.BinarySearchVisualizer;
import com.yufei.module.java.widget.visualizer.LinkedListControls;
import com.yufei.module.java.widget.visualizer.LinkedListVisualizer;
import com.yufei.module.java.widget.visualizer.SortingVisualizer;
import com.yufei.module.java.widget.visualizer.StackControls;
import com.yufei.module.java.widget.visualizer.StackVisualizer;
import com.yufei.module.java.widget.visualizer.graph.DirectedGraphVisualizer;
import com.yufei.module.java.widget.visualizer.graph.WeightedGraphVisualizer;
import com.yufei.module.java.widget.visualizer.graph.WeightedGraphVisualizer2;

import java.util.ArrayList;

public class VisualActivity extends BaseActivity {

    FloatingActionButton fab;
    AppBarLayout appBarLayout;

    LogFragment logFragment;
    CodeFragment codeFragment;
    DescrFragment algoFragment;
    ViewPager viewPager;
    TabLayout mTabHome;

    private ArrayList<String> mTitleList;
    private ArrayList<BaseFragment> mFragments ;

    Algorithm algorithm;
    String algCode;
    String startCommand;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_visual);
        
        algCode = getIntent().getStringExtra(Algorithm.KEY_ALGORITHM);
        startCommand = getIntent().getStringExtra(Algorithm.KEY_STARTCOMMAND);
        if(TextUtils.isEmpty(startCommand)){
            startCommand = Algorithm.COMMAND_START_ALGORITHM;
        }
        initView();

        mTitleList = new ArrayList<>(3);
        mFragments = new ArrayList<>(3);
        logFragment = LogFragment.newInstance();
        codeFragment = CodeFragment.newInstance(algCode);
        algoFragment = DescrFragment.newInstance(algCode);
        mTitleList.add("介绍");
        mTitleList.add("日志");
        mTitleList.add("源码");
        // mFragments.add(new ToutiaoFragment());
        mFragments.add(algoFragment);
        mFragments.add(logFragment);
        mFragments.add(codeFragment);

        initFragment(algCode);
    }
    

    private void initView(){
        viewPager = findViewById(R.id.vp_alg);
        mTabHome = findViewById(R.id.tab_home);
        fab = findViewById(R.id.fab_play);
        appBarLayout = findViewById(R.id.app_bar);
        ImageView ivBack = findViewById(R.id.iv_visual_back);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VisualActivity.this.finish();
            }
        });
    }

    private void setupViewPager(){
        viewPager.setOffscreenPageLimit(3);
        VPFragmentAdapter myAdapter = new VPFragmentAdapter(getSupportFragmentManager(), mFragments, mTitleList);
        viewPager.setAdapter(myAdapter);
        mTabHome.setTabMode(TabLayout.MODE_FIXED);
        mTabHome.setupWithViewPager(viewPager, true);
        mTabHome.setTabsFromPagerAdapter(myAdapter);
    }


    public void setStartCommand(String startCommand) {
        this.startCommand = startCommand;
    }

    public void initFragment(String algorithmKey) {
        viewPager.setOffscreenPageLimit(3);

        codeFragment.setCode(algorithmKey);
        algoFragment.setCodeDesc(algorithmKey);

        assert algorithmKey != null;
        final AlgorithmVisualizer visualizer;
        appBarLayout.removeAllViewsInLayout();

        fab.setVisibility(View.VISIBLE);

        switch (algorithmKey) {
            case Algorithm.BINARY_SEARCH:
                visualizer = new BinarySearchVisualizer(this);
                appBarLayout.addView(visualizer);
                algorithm = new BinarySearch((BinarySearchVisualizer) visualizer, this, logFragment);
                ((BinarySearch) algorithm).setData(DataUtils.createArray(15, true));
                break;
            case Algorithm.LINEAR_SEARCH:
                visualizer = new BinarySearchVisualizer(this);
                appBarLayout.addView(visualizer);
                algorithm = new LinearSearch((BinarySearchVisualizer) visualizer, this, logFragment);
                ((LinearSearch) algorithm).setData(DataUtils.createArray(15, false));
                break;
            case Algorithm.BUBBLE_SORT:
                visualizer = new SortingVisualizer(this);
                appBarLayout.addView(visualizer);
                algorithm = new BubbleSort((SortingVisualizer) visualizer, this, logFragment);
                ((BubbleSort) algorithm).setData(DataUtils.createRandomArray(15));
                break;
            case Algorithm.INSERTION_SORT:
                visualizer = new SortingVisualizer(this);
                appBarLayout.addView(visualizer);
                algorithm = new InsertionSort((SortingVisualizer) visualizer, this, logFragment);
                ((InsertionSort) algorithm).setData(DataUtils.createRandomArray(15));
                break;
            case Algorithm.SELECTION_SORT:
                visualizer = new SortingVisualizer(this);
                appBarLayout.addView(visualizer);
                algorithm = new SelectionSort((SortingVisualizer) visualizer, this, logFragment);
                ((SelectionSort) algorithm).setData(DataUtils.createRandomArray(15));
                break;
            case Algorithm.QUICKSORT:
                visualizer = new SortingVisualizer(this);
                appBarLayout.addView(visualizer);
                algorithm = new QuickSort((SortingVisualizer) visualizer, this, logFragment);
                ((QuickSort) algorithm).setData(DataUtils.createRandomArray(15));
                break;
            case Algorithm.BST_SEARCH:
                visualizer = new BSTVisualizer(this);
                appBarLayout.addView(visualizer);
                algorithm = new BSTAlgorithm((BSTVisualizer) visualizer, this, logFragment);
                ((BSTAlgorithm) algorithm).setData(DataUtils.createBinaryTree());
                break;
            case Algorithm.BST_INSERT:
                visualizer = new BSTVisualizer(this, 280);
                ArrayVisualizer arrayVisualizer = new ArrayVisualizer(this);
                appBarLayout.addView(visualizer);
                appBarLayout.addView(arrayVisualizer);
                algorithm = new BSTAlgorithm((BSTVisualizer) visualizer, this, logFragment);
                ((BSTAlgorithm) algorithm).setArrayVisualizer(arrayVisualizer);
                ((BSTAlgorithm) algorithm).setData(DataUtils.createBinaryTree());
                break;
            case Algorithm.LINKED_LIST:
                visualizer = new LinkedListVisualizer(this);
                LinkedListControls controls = new LinkedListControls(this, mTabHome, fab);
                appBarLayout.addView(visualizer);
                appBarLayout.addView(controls);
                algorithm = new LinkedList((LinkedListVisualizer) visualizer, this, logFragment);
                ((LinkedList) algorithm).setData(DataUtils.createLinkedList());
                controls.setLinkedList((LinkedList) algorithm);
                break;
            case Algorithm.STACK:
                visualizer = new StackVisualizer(this);
                StackControls stackcontrols = new StackControls(this, mTabHome, fab);
                appBarLayout.addView(visualizer);
                appBarLayout.addView(stackcontrols);
                algorithm = new Stack(5, (StackVisualizer) visualizer, this, logFragment);
                ((Stack) algorithm).setData(DataUtils.createStack());
                stackcontrols.setStack((Stack) algorithm);
                fab.setVisibility(View.GONE);
                break;
            case Algorithm.BFS:
            case Algorithm.DFS:
                visualizer = new DirectedGraphVisualizer(this);
                appBarLayout.addView(visualizer);
                algorithm = new GraphTraversalAlgorithm((DirectedGraphVisualizer) visualizer, this, logFragment);
                ((GraphTraversalAlgorithm) algorithm).setData(DataUtils.createDirectedGraph());
                break;
            case Algorithm.DIJKSTRA:
                visualizer = new WeightedGraphVisualizer2(this);
                appBarLayout.addView(visualizer);
                algorithm = new DijkstraAgorithm((WeightedGraphVisualizer2) visualizer, this, logFragment);
                ((DijkstraAgorithm) algorithm).setData(DataUtils.createWeightedGraph2(5));
                break;
            case Algorithm.BELLMAN_FORD:
                visualizer = new WeightedGraphVisualizer(this);
                appBarLayout.addView(visualizer);
                algorithm = new BellmanFordAlgorithm((WeightedGraphVisualizer) visualizer, this, logFragment);
                ((BellmanFordAlgorithm) algorithm).setData(DataUtils.createWeightedGraph(5));
                break;
            case Algorithm.N_QUEENS:
                visualizer = null;
                break;
            default:
                visualizer = null;
        }


        algorithm.setStarted(false);
        fab.setImageResource(R.mipmap.ic_play_arrow_white_24dp);
        logFragment.clearLog();

        algorithm.setCompletionListener(new AlgoCompletionListener() {
            @Override
            public void onAlgoCompleted() {
                fab.setImageResource(R.mipmap.ic_settings_backup_restore_white_24dp);
                if (visualizer != null)
                    visualizer.onCompleted();
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!algorithm.isStarted()) {
                    algorithm.sendMessage(startCommand);
                    fab.setImageResource(R.mipmap.ic_pause_white_24dp);
                    logFragment.clearLog();
                    //bottomBar.selectTabAtPosition(1, true);//move to log fragment
                    mTabHome.getTabAt(1).select();
                } else {
                    if (algorithm.isPaused()) {
                        algorithm.setPaused(false);
                        fab.setImageResource(R.mipmap.ic_pause_white_24dp);
                    } else {
                        algorithm.setPaused(true);
                        fab.setImageResource(R.mipmap.ic_play_arrow_white_24dp);
                    }
                }
            }
        });

        View shadow = LayoutInflater.from(this).inflate(R.layout.shadow, appBarLayout, false);
        appBarLayout.addView(shadow);

        setupViewPager();
    }

}
