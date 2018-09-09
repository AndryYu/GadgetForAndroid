package com.yufei.module.java.widget.visualizer;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.yufei.module.java.R;
import com.yufei.module.java.module.algorithm.entity.list.LinkedList;


public class LinkedListControls extends LinearLayout {

    LinkedList linkedList;
    TabLayout mTabHome;
    FloatingActionButton fab;

    public LinkedListControls(Context context, TabLayout bottomBar, FloatingActionButton fab) {
        super(context);
        this.mTabHome = bottomBar;
        this.fab = fab;
        initialise();
    }

    public LinkedListControls(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialise();
    }

    public void setLinkedList(LinkedList algorithm) {
        this.linkedList = algorithm;
    }


    private void initialise() {
        setOrientation(VERTICAL);

        View controls = LayoutInflater.from(getContext()).inflate(R.layout.linked_list_controls, this, false);

        final Button add, deleteFront, traverse;

        add = (Button) controls.findViewById(R.id.llc_add);
        deleteFront = (Button) controls.findViewById(R.id.llc_delete_front);
        traverse = (Button) controls.findViewById(R.id.llc_traverse);

        add.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //bottomBar.selectTabAtPosition(1,true);
                mTabHome.getTabAt(1).select();
                linkedList.sendMessage(LinkedList.ADD);
            }
        });
        deleteFront.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //bottomBar.selectTabAtPosition(1,true);
                mTabHome.getTabAt(1).select();
                linkedList.sendMessage(LinkedList.DELETE_FRONT);
            }
        });
        traverse.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                fab.performClick();
            }
        });
        addView(controls);
    }
}
