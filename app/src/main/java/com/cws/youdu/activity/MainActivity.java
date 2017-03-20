package com.cws.youdu.activity;

import android.support.v4.app.FragmentManager;

import com.cws.youdu.R;
import com.cws.youdu.base.BaseActivity;
import com.cws.youdu.fragment.NavFragment;
import com.cws.youdu.view.NavigationButton;

/**
 * Created by cws on 2017/3/10.
 */

public class MainActivity extends BaseActivity implements NavFragment.OnNavigationReselectListener {
    private NavFragment mNavBar;

    @Override
    protected int getContentView() {
        return R.layout.act_home;
    }

    @Override
    protected void initView() {
        FragmentManager manager = getSupportFragmentManager();
        mNavBar = ((NavFragment) manager.findFragmentById(R.id.fag_nav));
        mNavBar.setup(this, manager, R.id.main_container, this);
    }

    @Override
    public void onReselect(NavigationButton navigationButton) {
//        Fragment fragment = navigationButton.getFragment();
//        if (fragment != null
//                && fragment instanceof OnTabReselectListener) {
//            OnTabReselectListener listener = (OnTabReselectListener) fragment;
//            listener.onTabReselect();
//        }
    }
}
