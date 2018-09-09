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
import com.yufei.module.java.module.algorithm.entity.list.Stack;


public class StackControls extends LinearLayout {

    Stack stack;
    TabLayout mTabHome;
    FloatingActionButton fab;

    public StackControls(Context context, TabLayout bottomBar, FloatingActionButton fab) {
        super(context);
        this.mTabHome = bottomBar;
        this.fab = fab;
        initialise();
    }

    public StackControls(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialise();
    }

    public void setStack(Stack algorithm) {
        this.stack = algorithm;
    }


    private void initialise() {
        setOrientation(VERTICAL);

        View controls = LayoutInflater.from(getContext()).inflate(R.layout.stack_controls, this, false);

        final Button peek, push, pop;
        peek = (Button) controls.findViewById(R.id.sc_peek);
        push = (Button) controls.findViewById(R.id.sc_push);
        pop = (Button) controls.findViewById(R.id.sc_pop);

        peek.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
               // bottomBar.selectTabAtPosition(1, true);
                mTabHome.getTabAt(1).select();
                stack.sendMessage(Stack.PEEK);
            }
        });
        push.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //bottomBar.selectTabAtPosition(1, true);
                mTabHome.getTabAt(1).select();
                stack.sendMessage(Stack.PUSH);
            }
        });
        pop.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //bottomBar.selectTabAtPosition(1, true);
                mTabHome.getTabAt(1).select();
                stack.sendMessage(Stack.POP);
            }
        });


        addView(controls);
    }
}
