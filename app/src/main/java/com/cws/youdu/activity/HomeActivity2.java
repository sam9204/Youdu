package com.cws.youdu.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import com.cws.youdu.R;
import com.cws.youdu.base.BaseActivity;
import com.cws.youdu.fragment.DynamicTabFragment;
import com.cws.youdu.fragment.ExploreFragment;
import com.cws.youdu.fragment.TweetViewPagerFragment;
import com.cws.youdu.view.tab.BarEntity;
import com.cws.youdu.view.tab.BottomTabBar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cws on 2017/3/20.
 */
public class HomeActivity2 extends BaseActivity implements BottomTabBar.OnSelectListener{
    private BottomTabBar tb ;
    private List<BarEntity> bars ;
    private DynamicTabFragment homeFragment ;
    private TweetViewPagerFragment textJokeFragment ;
    private ExploreFragment imageJokeFragment ;
    private FragmentManager manager ;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_main);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_tab_main;
    }

    @Override
    protected void initView() {
        manager = getSupportFragmentManager();
        tb = (BottomTabBar) findViewById(R.id.tb);
        tb.setManager(manager);
        tb.setOnSelectListener(this);
        bars = new ArrayList<>();
        bars.add(new BarEntity("主页",R.drawable.tab_icon_new,R.drawable.tab_icon_new));
        bars.add(new BarEntity("段子",R.drawable.tab_icon_tweet,R.drawable.tab_icon_tweet));
        bars.add(new BarEntity("趣图",R.drawable.tab_icon_explore,R.drawable.tab_icon_explore));
        tb.setBars(bars);
    }

    @Override
    public void onSelect(int position) {
        switch (position){
            case 0:
                if (homeFragment==null){
                    homeFragment = new DynamicTabFragment();
                }
                tb.switchContent(homeFragment);
                break;
            case 1:
                if (textJokeFragment==null){
                    textJokeFragment = new TweetViewPagerFragment();
                }
                tb.switchContent(textJokeFragment);
                break;
            case 2:
                if (imageJokeFragment==null){
                    imageJokeFragment = new ExploreFragment();
                }
                tb.switchContent(imageJokeFragment);
                break;
            default:
                break;
        }
    }

}
