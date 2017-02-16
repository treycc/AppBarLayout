package com.treycc.appbarlayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import me.yokeyword.swipebackfragment.SwipeBackActivity;

/**
 * Created by treycc on 2017/2/8.
 */

public class NestScrollActivity extends SwipeBackActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_nestscroll);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        ScrollPagerAdapter scrollPagerAdapter = new ScrollPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(scrollPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    private class ScrollPagerAdapter extends FragmentPagerAdapter {

        public ScrollPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return new RecylerFragment();
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "第一";
                case 1:
                    return "第二";
                case 2:
                    return "第三";
            }
            return "";
        }
    }
}
