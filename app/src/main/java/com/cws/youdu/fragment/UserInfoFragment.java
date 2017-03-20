package com.cws.youdu.fragment;

import com.cws.youdu.R;
import com.cws.youdu.base.BaseTitleFragment;
import com.cws.youdu.interf.OnTabReselectListener;

/**
 * Created by cws on 2017/3/10.
 */

public class UserInfoFragment extends BaseTitleFragment  implements OnTabReselectListener {
    @Override
    protected int getContentLayoutId() {
        return R.layout.act;
    }

    @Override
    protected int getTitleRes() {
        return R.string.main_tab_name_my;
    }

    @Override
    public void onTabReselect() {

    }
}
