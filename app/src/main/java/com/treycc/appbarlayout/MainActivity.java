package com.treycc.appbarlayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.treycc.appbarlayout.bottomnavigate.BadgeItem;
import com.treycc.appbarlayout.bottomnavigate.BottomNavigationBar;
import com.treycc.appbarlayout.bottomnavigate.BottomNavigationItem;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "tag";
    private BottomNavigationBar navigationBar;
    private MentionEditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
        getWindow().getDecorView().setSystemUiVisibility(option);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.TRANSPARENT);
//            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }

//        TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayout);
//        tabLayout.addTab(tabLayout.newTab().setText("tab1"));
//        tabLayout.addTab(tabLayout.newTab().setText("tab2"));


        final CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbarLayout);

        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar_layout);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
//                if (verticalOffset == 0) {
//                    collapsingToolbarLayout.setTitle("");
//                    collapsingToolbarLayout.setTitle("已经展开");
//                } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
//                    collapsingToolbarLayout.setTitle("已经关闭");
//                    collapsingToolbarLayout.setTitle("标题");
//                } else {
//                    collapsingToolbarLayout.setTitle("过程中");
//                    collapsingToolbarLayout.setTitle("");
//                }
            }
        });

        //---------------------------------------------------------------------------------

        navigationBar = (BottomNavigationBar) findViewById(R.id.navigateBar);
        navigationBar.setMode(BottomNavigationBar.MODE_FIXED);

        BadgeItem numberBadgeItem = new BadgeItem()
                .setBorderWidth(4)
                .setBackgroundColor(Color.RED)
                .setText("0");


        navigationBar.addItem(new BottomNavigationItem(android.R.drawable.ic_menu_camera, "相机"));
        navigationBar.addItem(new BottomNavigationItem(android.R.drawable.ic_menu_compass, "位置").setBadgeItem(numberBadgeItem));
        navigationBar.addItem(new BottomNavigationItem(android.R.drawable.ic_menu_search, "搜索"));
        navigationBar.addItem(new BottomNavigationItem(android.R.drawable.ic_menu_help, "帮助"));
        navigationBar.initialise();

        navigationBar.setAutoHideEnabled(true);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        navigationBar.setFab(fab);

        mEditText = (MentionEditText) findViewById(R.id.mention_edittext);
        mEditText.setMentionTextColor(Color.BLUE);
        mEditText.setPattern("@[\\u4e00-\\u9fa5\\w\\-]+");
        mEditText.setOnMentionInputListener(new MentionEditText.OnMentionInputListener() {
            @Override
            public void onMentionCharacterInput() {
                Log.w(TAG, "onMentionCharacterInput: what");
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Slide slide1 = new Slide();
            slide1.setSlideEdge(Gravity.BOTTOM);
            Slide slide2 = new Slide();
            slide2.setSlideEdge(Gravity.TOP);
            //A-B—A  A调用Exit和Reenter,B调用Enter和Return
            getWindow().setEnterTransition(slide1);
            getWindow().setExitTransition(slide1);
            getWindow().setReturnTransition(slide1);
            getWindow().setReenterTransition(slide1);
        }

    }

    public void itemClick(View view) {
        List<String> mentionList = mEditText.getMentionList(true);
        for (String s : mentionList) {
            Log.w(TAG, s);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();//返回
                return true;
            case R.id.feedback:
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(this);
                ActivityCompat.startActivity(this, new Intent(this, FirstActivity.class), options.toBundle());
                break;
            case R.id.about:
                Toast.makeText(this, "关于页面还在路上~", Toast.LENGTH_SHORT).show();

        }

        return super.onOptionsItemSelected(item);

    }
}
