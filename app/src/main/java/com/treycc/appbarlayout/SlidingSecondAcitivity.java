package com.treycc.appbarlayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Field;

/**
 * Created by treycc on 2017/2/14.
 */

public class SlidingSecondAcitivity extends AppCompatActivity implements SlidingPaneLayout.PanelSlideListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_second);
        SlidingPaneLayout slidingPaneLayout = new SlidingPaneLayout(this);
        // 通过反射改变mOverhangSize的值为0，
        // 这个mOverhangSize值为菜单到右边屏幕的最短距离，
        // 默认是32dp，现在给它改成0
        try {
            Field overhangSize = SlidingPaneLayout.class.getDeclaredField("mOverhangSize");
            overhangSize.setAccessible(true);
            overhangSize.set(slidingPaneLayout, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        slidingPaneLayout.setPanelSlideListener(this);
        slidingPaneLayout.setSliderFadeColor(getResources()
                .getColor(android.R.color.transparent));

        // 左侧的透明视图
        View leftView = new View(this);
        leftView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        slidingPaneLayout.addView(leftView, 0);

        ViewGroup decorView = (ViewGroup) getWindow().getDecorView();

        // 右侧的内容视图
        ViewGroup decorChild = (ViewGroup) decorView.getChildAt(0);
        decorChild.setBackgroundColor(getResources()
                .getColor(android.R.color.white));
        decorView.removeView(decorChild);
        decorView.addView(slidingPaneLayout);

        // 为 SlidingPaneLayout 添加内容视图
        slidingPaneLayout.addView(decorChild, 1);

    }

    @Override
    public void onPanelSlide(View panel, float slideOffset) {

    }

    @Override
    public void onPanelOpened(View panel) {
        finish();
        overridePendingTransition(0, 0);
    }

    @Override
    public void onPanelClosed(View panel) {

    }
}
