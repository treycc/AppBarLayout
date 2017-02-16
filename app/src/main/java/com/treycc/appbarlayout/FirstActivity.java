package com.treycc.appbarlayout;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Outline;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.transition.TransitionManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.AppCompatSpinner;
import android.transition.Slide;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.view.WindowManager;
import android.widget.ArrayAdapter;

import me.yokeyword.swipebackfragment.SwipeBackActivity;


/**
 * Created by treycc on 2017/2/6.
 */

public class FirstActivity extends SwipeBackActivity {

    private static final String TAG = "First";
    private View shareEditText;
    private View compactEditText;
    private View shareImage;
    private View sharecontent;
    private View icon;
    private AppCompatSpinner spinner;

    @Override
    public boolean swipeBackPriority() {
        return false;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        shareEditText = findViewById(R.id.share_elemet);
        shareImage = findViewById(R.id.share_elemet1);
        compactEditText = findViewById(R.id.compatelemet);
        sharecontent = findViewById(R.id.share_content);

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            getWindow().getDecorView().setSystemUiVisibility(option);
        }

        icon = findViewById(R.id.icon);
        spinner = (AppCompatSpinner) findViewById(R.id.spinner);
        spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, new String[]{"aaaa", "bbbb", "cccc", "dddd"}));
        ViewCompat.setTransitionName(icon, "reveal");


        icon.post(new Runnable() {
            @Override
            public void run() {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    icon.setOutlineProvider(new ViewOutlineProvider() {
                        @Override
                        public void getOutline(View view, Outline outline) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                outline.setOval(0, 0, view.getWidth(), view.getHeight());
                            }
                        }
                    });
                    icon.setClipToOutline(true);
                }
            }
        });

    }

    public void sliding(View v) {
        startActivity(new Intent(this, SlidingPaneLayoutActivity.class));
    }

    public void coornadate(View v) {
        CoordinatorFragment coordinatorFragment = new CoordinatorFragment();
        Slide slideTransition = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            slideTransition = new Slide(Gravity.END);
            slideTransition.setDuration(400);
            coordinatorFragment.setEnterTransition(slideTransition);
        }
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, coordinatorFragment)
                .addToBackStack(null)
                .commit();
    }

    public void nestScroll(View v) {
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(this);
        ActivityCompat.startActivity(this, new Intent(this, NestScrollActivity.class), options.toBundle());
    }

    public void shareClick(View v) {
        ActivityOptionsCompat nihao = ActivityOptionsCompat.makeSceneTransitionAnimation(this, new Pair<View, String>(shareEditText, "nihao"), new Pair<>(shareImage, "nihao1"), new Pair<View, String>(sharecontent, "nihao3"));
        ActivityCompat.startActivity(this, new Intent(this, SecondActivity.class), nihao.toBundle());
    }

    public void iconClick(View v) {
        TransitionManager.beginDelayedTransition((ViewGroup) getWindow().getDecorView().getRootView());
        ViewGroup.LayoutParams layoutParams = icon.getLayoutParams();
        int i = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, getResources().getDisplayMetrics());
        layoutParams.width = i;
        layoutParams.height = i;
        icon.setLayoutParams(layoutParams);
    }

    public void back(View v) {
        TransitionManager.beginDelayedTransition((ViewGroup) getWindow().getDecorView().getRootView());
        ViewGroup.LayoutParams layoutParams = icon.getLayoutParams();
        int i = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, getResources().getDisplayMetrics());
        layoutParams.width = i;
        layoutParams.height = i;
        icon.setLayoutParams(layoutParams);
    }

    public void goReveal(View v) {
        ActivityOptionsCompat rewrwer = ActivityOptionsCompat.makeSceneTransitionAnimation(this, new Pair<View, String>(icon, "reveal"));
        ActivityCompat.startActivity(this, new Intent(this, ReavalActivity.class), rewrwer.toBundle());
    }

    public void compactClick(View v) {
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("x", compactEditText.getX());
        intent.putExtra("y", compactEditText.getY());
        intent.putExtra("width", compactEditText.getWidth());
        startActivity(intent);
        overridePendingTransition(0, 0);
    }

}
