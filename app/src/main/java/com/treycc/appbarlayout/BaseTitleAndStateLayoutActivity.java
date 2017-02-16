package com.treycc.appbarlayout;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.commen.widget.statelayout.StateLayout;


/**
 * Created by treycc on 2017/2/16.
 */

public abstract class BaseTitleAndStateLayoutActivity extends AppCompatActivity {

    private LinearLayout linearContainer;
    private Toolbar toolbar;
    private StateLayout stateLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        linearContainer = new LinearLayout(this);
        linearContainer.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        linearContainer.setOrientation(LinearLayout.VERTICAL);
        if (supportTitleBar()) {
            toolbar = (Toolbar) LayoutInflater.from(this).inflate(R.layout.include_toolbar, linearContainer, true).findViewById(R.id.toolbar);
            toolbar.setNavigationIcon(R.drawable.arrow_left);
            toolbar.setTitle(getResources().getString(R.string.app_name));
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        FrameLayout contentFrame = new FrameLayout(this);
        contentFrame.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        View contentView = LayoutInflater.from(this).inflate(layoutResID, null);
        contentFrame.addView(contentView);
        if (supportStateLayout()) {
            stateLayout = new StateLayout(this);
            stateLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            contentFrame.addView(stateLayout);
        }
        linearContainer.addView(contentFrame);
        super.setContentView(linearContainer);
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    public StateLayout getStateLayout() {
        return stateLayout;
    }

    public abstract boolean supportTitleBar();

    public abstract boolean supportStateLayout();

}
