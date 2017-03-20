package com.cws.youdu.fragment;

import com.cws.youdu.R;
import com.cws.youdu.base.BaseTitleFragment;
import com.cws.youdu.interf.OnTabReselectListener;
import com.cws.youdu.okhttp.RequestCenter;
import com.cws.youdu.okhttp.listener.CallbackListener;

/**
 * Created by cws on 2017/3/10.
 */

public class DynamicTabFragment extends BaseTitleFragment  implements OnTabReselectListener {
    @Override
    protected int getContentLayoutId() {
        return R.layout.act;
    }

    @Override
    protected int getTitleRes() {
        return R.string.main_tab_name_news;
    }

    @Override
    protected void initData() {
        super.initData();
        RequestCenter.requestRecommandData(new CallbackListener() {
            @Override
            public void onSuccess(Object responseObj) {

            }

            @Override
            public void onFailure(Object reasonObj) {
            }
        });
    }

    @Override
    public void onTabReselect() {

    }
}
