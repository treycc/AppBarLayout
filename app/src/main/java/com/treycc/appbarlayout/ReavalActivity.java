package com.treycc.appbarlayout;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.transition.ArcMotion;
import android.transition.ChangeBounds;
import android.transition.ChangeClipBounds;
import android.transition.ChangeTransform;
import android.transition.Fade;
import android.transition.TransitionManager;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.BounceInterpolator;
import android.view.animation.PathInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;

import me.yokeyword.swipebackfragment.SwipeBackActivity;

import static com.treycc.appbarlayout.R.id.toolbar;

/**
 * Created by treycc on 2017/2/7.
 */

public class ReavalActivity extends AppCompatActivity {

    private View icon;
    private ViewGroup container;
    private LinearLayout textContainer;
    private View click;
    private View text1;
    private TextView text2;
    private float textSize;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reveal);
        icon = findViewById(R.id.icon_image);
        container = (ViewGroup) findViewById(R.id.reveal_container);
        textContainer = (LinearLayout) findViewById(R.id.text_container);
        click = findViewById(R.id.click);
        text1 = findViewById(R.id.text1);
        text2 = (TextView) findViewById(R.id.text2);
        textSize = text2.getTextSize();
        ViewCompat.setTransitionName(icon, "reveal");

        Toolbar toobar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toobar);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }

        View decorView = getWindow().getDecorView();


        Transition transition = getWindow().getSharedElementEnterTransition();
        transition.setDuration(400);
//        transition.setInterpolator(new PathInterpolator(0.5f,1,1,1));x`
        transition.setPathMotion(new ArcMotion());


        transition.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {

            }

            @Override
            public void onTransitionEnd(Transition transition) {
//                animateIcon();
                transitionLayout();
            }

            @Override
            public void onTransitionCancel(Transition transition) {

            }

            @Override
            public void onTransitionPause(Transition transition) {

            }

            @Override
            public void onTransitionResume(Transition transition) {
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void transitionLayout() {
        container.setBackgroundResource(android.R.color.holo_blue_light);
        Animator circularReveal = ViewAnimationUtils.createCircularReveal(container, (int) icon.getX() + icon.getWidth() / 2, (int) icon.getX() + icon.getWidth() / 2, icon.getWidth() / 2, (int) Math.hypot(container.getWidth(), container.getHeight()));
        circularReveal.setDuration(500);
        circularReveal.start();
        circularReveal.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                addContent();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void click(View v) {
        TransitionManager.beginDelayedTransition(textContainer);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) text1.getLayoutParams();
        if (layoutParams.gravity == Gravity.END) {
            layoutParams.gravity = Gravity.NO_GRAVITY;
        } else {
            layoutParams.gravity = Gravity.END;
        }
        text1.setLayoutParams(layoutParams);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void textClick(View v) {
//        ChangeTransform changeTransform = new ChangeTransform();
        ChangeBounds changeTransform = new ChangeBounds();
        TransitionManager.beginDelayedTransition(textContainer, changeTransform);
        if (text2.getTextSize() > textSize) {
            text2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        } else {
            text2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        }
    }


    private void addContent() {
        for (int i = 0; i < textContainer.getChildCount(); i++) {
            final View childAt = textContainer.getChildAt(i);
            childAt.postDelayed(new Runnable() {
                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void run() {
                    TransitionSet transitionSet = new TransitionSet();
                    transitionSet.addTransition(new Slide(Gravity.LEFT));
                    TransitionManager.beginDelayedTransition(textContainer, transitionSet);
                    childAt.setVisibility(View.VISIBLE);
                }
            }, 100 * i);

        }

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void animateIcon() {
        ObjectAnimator objectAnimatorX = ObjectAnimator.ofFloat(icon, "translationX", 0, 500);
        ObjectAnimator objectAnimatorY = ObjectAnimator.ofFloat(icon, "translationY", 0, 500);
        objectAnimatorX.setDuration(400);
        objectAnimatorX.setInterpolator(new PathInterpolator(0.5f, 1f));
        objectAnimatorX.start();
        objectAnimatorY.setDuration(400);
        objectAnimatorY.setInterpolator(new PathInterpolator(0.5f, 1f));
        objectAnimatorY.start();
    }
}
