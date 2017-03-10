package com.youjuke.optimalmaterialtreasure.app.order;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.youjuke.library.base.BaseActivity;
import com.youjuke.library.base.BaseFragment;
import com.youjuke.optimalmaterialtreasure.R;
import com.youjuke.optimalmaterialtreasure.app.MainActivity;
import com.youjuke.optimalmaterialtreasure.weights.MyView;

import java.util.ArrayList;

/**
 * 描述：订单界面
 * author：zyb
 * Created by Administrator on 2017/2/8.
 */

public class OrderFragment extends BaseFragment implements ViewPager.OnPageChangeListener, View.OnClickListener {

    public ViewPager getPager() {
        return pager;
    }

    public void setPager(ViewPager pager) {
        this.pager = pager;
    }

    ViewPager pager = null;
    private View tabline;
    private int mTabLineWidth;
    private ArrayList<MyView> title = new ArrayList<MyView>();
    ArrayList<OrderDetailFragment> fragments = new ArrayList<>();
    private MyView tv1;
    private MyView tv2;
    private MyView tv3;
    private MyView tv4;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_order;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (this.isVisible())
            fragments.get(0).initData();
    }

    @Override
    public void finishCreateView(Bundle state) {
        initView();
    }

    private void initView() {
        tv1 = $(R.id.tv1);
        tv2 = $(R.id.tv2);
        tv3 = $(R.id.tv3);
        tv4 = $(R.id.tv4);
        tv1.setOnClickListener(this);
        tv2.setOnClickListener(this);
        tv3.setOnClickListener(this);
        tv4.setOnClickListener(this);
        init();
        pager = $(R.id.viewpager);
        pager.setAdapter(new FragmentPagerAdapter(((BaseActivity) mContext).getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });
        pager.addOnPageChangeListener(this);
        pager.setCurrentItem(((MainActivity) getActivity()).getCurrent());
//        fragments.get(0).initData();
        title.get(0).setAlpha(1);
        intTabLine();
    }

    private void intTabLine() {
        tabline = $(R.id.tab_line);
        Display display = ((BaseActivity) mContext).getWindow().getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);
        mTabLineWidth = outMetrics.widthPixels / 4;
        ViewGroup.LayoutParams lp = tabline.getLayoutParams();
        lp.width = mTabLineWidth;
        tabline.setLayoutParams(lp);
    }


    private void init() {
        title.add(tv1);
        title.add(tv2);
        title.add(tv3);
        title.add(tv4);
        fragments.add(new OrderDetailFragment(mContext, "全部"));
        fragments.add(new OrderDetailFragment(mContext, "dfk"));
        fragments.add(new OrderDetailFragment(mContext, "dfh"));
        fragments.add(new OrderDetailFragment(mContext, "dsh"));


    }

    public static OrderFragment newInstance() {
        return new OrderFragment();
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        LinearLayout.LayoutParams lp = (android.widget.LinearLayout.LayoutParams) tabline.getLayoutParams();
        lp.leftMargin = (int) ((position + positionOffset) * mTabLineWidth);
        tabline.setLayoutParams(lp);
        if (position > fragments.size()) {
            pager.setCurrentItem(1);
        }
        for (int i = 0; i < 4; i++) {
            if (i == position) {
                title.get(i).setTextColor(0xee0005);
            } else {
                title.get(i).setTextColor(0x333333);
            }

        }
    }
    int i=0;
    @Override
    public void onPageSelected(int position) {
        if (MainActivity.first == 0)
            fragments.get(position).initData();
        i=position;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden){
            fragments.get(i).initData();
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv1:
                pager.setCurrentItem(0, true);
                tv1.setTextColor(0xee0005);
                tv2.setTextColor(0x333333);
                tv3.setTextColor(0x333333);
                tv4.setTextColor(0x333333);
                break;
            case R.id.tv2:
                pager.setCurrentItem(1, true);
                tv1.setTextColor(0x333333);
                tv2.setTextColor(0xee0005);
                tv3.setTextColor(0x333333);
                tv4.setTextColor(0x333333);
                break;
            case R.id.tv3:
                pager.setCurrentItem(2, true);
                tv1.setTextColor(0x333333);
                tv2.setTextColor(0x333333);
                tv3.setTextColor(0xee0005);
                tv4.setTextColor(0x333333);
                break;
            case R.id.tv4:
                pager.setCurrentItem(3, true);
                tv1.setTextColor(0x333333);
                tv2.setTextColor(0x333333);
                tv3.setTextColor(0x333333);
                tv4.setTextColor(0xee0005);
                break;
        }
    }
}
