package com.commen.widget.statelayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * Created by treycc on 2017/2/16.
 */

public class StateLayout extends FrameLayout {

    private static final String TAG = "StateLayout";

    enum State {
        LOADING, EMPTY, ERROR, NETERROR, SUCCESS
    }

    private View mLoadingView;
    private View mEmptyView;
    private View mErrorView;
    private View mNetErrorView;

    private static final State DEFAULT_STATE = State.LOADING;
    private State state = DEFAULT_STATE;

    private OnClickListener mOnRetryClickListener;

    public StateLayout(Context context) {
        this(context, null, 0);
    }

    public StateLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StateLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public State getState() {
        return state;
    }

    private void clearState() {
        for (int i = 0; i < getChildCount(); i++) {
            View childView = getChildAt(i);
            childView.setVisibility(GONE);
        }
    }

    private void show(State state) {
        this.state = state;
        clearState();
        if (state == State.LOADING) {
            if (mLoadingView == null) {
                mLoadingView = LayoutInflater.from(getContext()).inflate(R.layout.statelayout_loading_view, this, false);
                addView(mLoadingView);
            } else {
                mLoadingView.setVisibility(VISIBLE);
            }
        }
        if (state == State.EMPTY) {
            if (mEmptyView == null) {
                mEmptyView = LayoutInflater.from(getContext()).inflate(R.layout.statelayout_empty_view, this, false);
                addView(mEmptyView);
            } else {
                mEmptyView.setVisibility(VISIBLE);
            }
            if (mOnRetryClickListener != null)
                mEmptyView.setOnClickListener(mOnRetryClickListener);

        }
        if (state == State.ERROR) {
            if (mErrorView == null) {
                mErrorView = LayoutInflater.from(getContext()).inflate(R.layout.statelayout_error_view, this, false);
                addView(mErrorView);
            } else {
                mErrorView.setVisibility(VISIBLE);
            }
            if (mOnRetryClickListener != null)
                mErrorView.setOnClickListener(mOnRetryClickListener);
        }
        if (state == State.NETERROR) {
            if (mNetErrorView == null) {
                mNetErrorView = LayoutInflater.from(getContext()).inflate(R.layout.statelayout_neterror_view, this, false);
                addView(mNetErrorView);
            } else {
                mNetErrorView.setVisibility(VISIBLE);
            }
            if (mOnRetryClickListener != null)
                mNetErrorView.setOnClickListener(mOnRetryClickListener);
        }
    }

    public void showEmpty() {
        show(State.EMPTY);
    }

    public void showLoading() {
        show(State.LOADING);
    }

    public void showError() {
        show(State.ERROR);
    }

    public void showNetError() {
        show(State.NETERROR);
    }

    public void showSuccess() {
        show(State.SUCCESS);
    }

    public void setOnRetryClickListener(OnClickListener onRetryClickListener) {
        this.mOnRetryClickListener = onRetryClickListener;
    }

}
