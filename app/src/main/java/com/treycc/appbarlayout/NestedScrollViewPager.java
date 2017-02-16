package com.treycc.appbarlayout;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by treycc on 2017/2/9.
 */

public class NestedScrollViewPager extends ViewPager {

    private static final String TAG = "NestedScrollViewPager";
    private int mLastX;
    private int mLastY;

    public NestedScrollViewPager(Context context) {
        super(context);
    }

    public NestedScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean isIntercepted = false;
        int x = (int) ev.getX();
        int y = (int) ev.getY();
        int dx = x - mLastX;
        int dy = y - mLastY;

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                if (Math.abs(dy) < Math.abs(dx)) {
                    isIntercepted = true;
                } else {
                    isIntercepted = false;
                }
                break;
            case MotionEvent.ACTION_UP:
                isIntercepted = false;
            default:
                break;
        }

        mLastX = x;
        mLastY = y;

        Log.w(TAG, "onInterceptTouchEvent: " + "dy  :  " + dy + "   dx   :   " + dx + "----" + isIntercepted + "   mLastx:mLastY   " + mLastX + " : " + mLastY);
        return isIntercepted;
    }
}
