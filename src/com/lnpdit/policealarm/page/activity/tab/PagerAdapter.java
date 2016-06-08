package com.lnpdit.policealarm.page.activity.tab;

import java.util.ArrayList;

import com.lnpdit.policealarm.R;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PagerAdapter extends FragmentPagerAdapter implements
		OnPageChangeListener, OnClickListener {
	private ArrayList<Fragment> fragmentsList;
	private ViewPager viewPager;
	private LinearLayout llTab;
	private Context context;

	public PagerAdapter(Context context, FragmentManager fm,
			ArrayList<Fragment> fragments, ViewPager viewPager,
			LinearLayout llTab) {
		super(fm);
		this.context = context;
		this.fragmentsList = fragments;
		this.viewPager = viewPager;
		this.llTab = llTab;

		this.viewPager.setOnPageChangeListener(this);

		for (int i = 0; i < llTab.getChildCount(); i++) {
			llTab.getChildAt(i).setOnClickListener(this);
		}
	}

	@Override
	public Fragment getItem(int position) {

		// Fragment fragment = new DummySectionFragment();
		// Bundle args = new Bundle();
		// args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, position +
		// 1);
		// fragment.setArguments(args);

		return fragmentsList.get(position);
	}

	@Override
	public int getCount() {
		return fragmentsList.size();
	}

	// ******************OnPageChangeListener***************
	@Override
	public void onPageScrollStateChanged(int state) {
		System.out.println("onPageScrollStateChanged:" + state);
	}

	@Override
	public void onPageScrolled(int position, float positionOffset,
			int positionOffsetPixels) {
		System.out.println("onPageScrolled:" + position + ">>" + positionOffset
				+ ">>" + positionOffsetPixels);
	}

	@Override
	public void onPageSelected(int position) {
		System.out.println("onPageSelected:" + position);
		for (int i = 0; i < llTab.getChildCount(); i++) {
			if (position == i) {
				View v = llTab.getChildAt(i);
				v.setBackgroundResource(R.drawable.bg_view_pager_scroll_selected);
				TextView tv = (TextView) v;
				tv.setTextColor(Color.rgb(153, 153, 153));
			} else {
				View v = llTab.getChildAt(i);
				v.setBackgroundResource(R.drawable.bg_view_pager_scroll_unselect);
				TextView tv = (TextView) v;
				tv.setTextColor(Color.rgb(221, 221, 221));
			}
		}
	}

	// ******************OnPageChangeListener***************

	@Override
	public void onClick(View view) {
		for (int i = 0; i < llTab.getChildCount(); i++) {
			if (view.equals(llTab.getChildAt(i))) {
				View v = llTab.getChildAt(i);
				v.setBackgroundResource(R.drawable.bg_view_pager_scroll_selected);
				TextView tv = (TextView) v;
				tv.setTextColor(Color.rgb(153, 153, 153));
				viewPager.setCurrentItem(i);
			} else {
				View v = llTab.getChildAt(i);
				v.setBackgroundResource(R.drawable.bg_view_pager_scroll_unselect);
				TextView tv = (TextView) v;
				tv.setTextColor(Color.rgb(221, 221, 221));
			}
		}
	}
}