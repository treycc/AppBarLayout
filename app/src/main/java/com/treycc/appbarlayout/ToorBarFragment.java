package com.treycc.appbarlayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by treycc on 2017/2/15.
 */

public class ToorBarFragment extends BaseTitleBarFragment {

    public ToorBarFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_toolbar, container, false);
        return setWithToolBar(inflate);
    }

}
