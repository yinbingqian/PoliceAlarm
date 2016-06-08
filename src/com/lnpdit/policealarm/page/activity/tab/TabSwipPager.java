package com.lnpdit.policealarm.page.activity.tab;

import java.util.ArrayList;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;

public class TabSwipPager {

	private CustomViewManager customView;

	private Context context;
	private LinearLayout parentView, llTab;
	private FragmentManager fm;
	private ViewPager viewPager;
	private PagerAdapter pagerAdapter;

	public TabSwipPager(Context context, FragmentManager fm,
			LinearLayout parentView) {
		this.context = context;
		this.fm = fm;
		this.parentView = parentView;
	}

	public boolean setFragmentList(ArrayList<Fragment> fragmentsList,
			String[] tags) {
		if (tags.length != fragmentsList.size()) {
			return false;
		}

		customView = new CustomViewManager(context, tags);
		llTab = customView.getTabView();
		viewPager = customView.getViewPager();

		pagerAdapter = new PagerAdapter(context, fm, fragmentsList, viewPager,
				llTab);
		viewPager.setAdapter(pagerAdapter);

		parentView.addView(customView.getCustomTabView(), new LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		return true;
	}

}
