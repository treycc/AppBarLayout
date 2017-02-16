package com.treycc.appbarlayout;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.commen.widget.statelayout.StateLayout;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;


/**
 * Created by treycc on 2017/2/14.
 */

public class SlidingPaneLayoutActivity extends BaseTitleAndStateLayoutActivity {

    private StateLayout stateLayout;
    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slidingpanelayout);
        toolbar = getToolbar();
        if (toolbar != null) {
            toolbar.inflateMenu(R.menu.menu_state);
            toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    if (stateLayout == null) {
                        return false;
                    }
                    switch (item.getItemId()) {
                        case R.id.state_empty:
                            stateLayout.showEmpty();
                            return true;
                        case R.id.state_error:
                            stateLayout.showError();
                            return true;
                        case R.id.state_neterror:
                            stateLayout.showNetError();
                            return true;
                        case R.id.state_loading:
                            stateLayout.showLoading();
                            return true;
                        case R.id.state_success:
                            stateLayout.showSuccess();
                            return true;
                    }
                    return false;
                }
            });
        }
        stateLayout = getStateLayout();
        if (stateLayout == null) {
            stateLayout.setOnRetryClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    stateLayout.showSuccess();
                }
            });
        }


    }

    @Override
    public boolean supportTitleBar() {
        return false;
    }

    @Override
    public boolean supportStateLayout() {
        return true;
    }

    public void nextpage(View v) {
        startActivity(new Intent(this, SlidingSecondAcitivity.class));
    }

    public void recylerpage(View v) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ToorBarFragment(), ToorBarFragment.class.getSimpleName()).addToBackStack(ToorBarFragment.class.getSimpleName()).commit();
    }

    public void state_switch(View v) {
        if (stateLayout != null) {
            stateLayout.showLoading();
        }
    }

}
