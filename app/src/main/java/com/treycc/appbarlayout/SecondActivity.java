package com.treycc.appbarlayout;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Created by treycc on 2017/2/6.
 */

public class SecondActivity extends AppCompatActivity {

    private static final String TAG = "Second";
    private EditText shareSecond;
    private View compactEditText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        shareSecond = (EditText) findViewById(R.id.share_elemet);
        compactEditText = findViewById(R.id.compatelemet_2);


        compactEditText.post(new Runnable() {
            @Override
            public void run() {
                enterAnimator();
            }
        });

    }

    private void enterAnimator() {
        float originY = getIntent().getFloatExtra("y", 0);
        float translateY = originY - compactEditText.getY();
        float y = compactEditText.getY();
        ViewCompat.setTranslationY(compactEditText, translateY);

        int w = compactEditText.getWidth();
        int width = getIntent().getIntExtra("width", 0);
        ViewGroup.LayoutParams layoutParams = compactEditText.getLayoutParams();
        layoutParams.width = width;
        compactEditText.setLayoutParams(layoutParams);


        ValueAnimator yAnimator = ValueAnimator.ofFloat(compactEditText.getY(), y);
        yAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                ViewCompat.setY(compactEditText, (Float) animation.getAnimatedValue());
            }
        });
        ValueAnimator wAnimator = ValueAnimator.ofInt(width, w);
        wAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                ViewGroup.LayoutParams layoutParams = compactEditText.getLayoutParams();
                layoutParams.width = (int) animation.getAnimatedValue();
                compactEditText.setLayoutParams(layoutParams);
            }
        });
        yAnimator.setDuration(300);
        wAnimator.setDuration(300);
        yAnimator.start();
        wAnimator.start();
    }

    private void exitAnimator() {
        float originY = getIntent().getFloatExtra("y", 0);
        int width = getIntent().getIntExtra("width", 0);

        ValueAnimator yAnimator = ValueAnimator.ofFloat(compactEditText.getY(), originY);
        yAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                ViewCompat.setY(compactEditText, (Float) animation.getAnimatedValue());
            }
        });
        ValueAnimator wAnimator = ValueAnimator.ofInt(compactEditText.getWidth(), width);
        wAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                ViewGroup.LayoutParams layoutParams = compactEditText.getLayoutParams();
                layoutParams.width = (int) animation.getAnimatedValue();
                compactEditText.setLayoutParams(layoutParams);
            }
        });
        yAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                finish();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        yAnimator.setDuration(300);
        wAnimator.setDuration(300);
        yAnimator.start();
        wAnimator.start();
    }

    @Override
    public void onBackPressed() {
//        exitAnimator();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            finishAfterTransition();
        } else {
            exitAnimator();
        }

    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0, 0);
    }
}
