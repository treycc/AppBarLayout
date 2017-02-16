package com.treycc.appbarlayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.concurrent.Executors;

import me.yokeyword.swipebackfragment.SwipeBackFragment;


/**
 * Created by treycc on 2017/2/9.
 */

public class CoordinatorFragment extends SwipeBackFragment {

    private TextView nestedTextView;
    private StringBuilder text = new StringBuilder();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_coordinator, container, false);
        nestedTextView = (TextView) inflate.findViewById(R.id.nestd_text);
        return attachToSwipeBack(inflate);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Executors.newFixedThreadPool(1).execute(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    text.append("wo");
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        nestedTextView.setText(text);
                    }
                });
            }
        });
    }
}
