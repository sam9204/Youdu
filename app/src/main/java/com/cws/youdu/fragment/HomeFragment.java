package com.cws.youdu.fragment;

import com.cws.youdu.R;
import com.cws.youdu.base.BaseTitleFragment;
import com.cws.youdu.interf.OnTabReselectListener;

/**
 * Created by cws on 2017/3/10.
 */

public class HomeFragment extends BaseTitleFragment  implements OnTabReselectListener {
    @Override
    protected int getContentLayoutId() {
        return R.layout.fra_home;
    }

    @Override
    protected int getTitleRes() {
        return R.string.main_tab_name_news;
    }

    @Override
    public void onTabReselect() {

    }
}
