package com.treycc.appbarlayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by treycc on 2017/2/16.
 */

public class BaseTitleBarFragment extends Fragment {

    private LinearLayout linearContainer;
    private Toolbar toolbar;

    protected View setWithToolBar(View view) {
        linearContainer = new LinearLayout(getContext());
        linearContainer.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        linearContainer.setOrientation(LinearLayout.VERTICAL);
        toolbar = (Toolbar) LayoutInflater.from(getContext()).inflate(R.layout.include_toolbar, linearContainer, true).findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.arrow_left);
        toolbar.setTitle(getResources().getString(R.string.app_name));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getActivity() != null) {
                    getActivity().getSupportFragmentManager().popBackStackImmediate();
                }
            }
        });
        linearContainer.addView(view);
        return linearContainer;
    }

    public Toolbar getToolbar() {
        return toolbar;
    }
}
