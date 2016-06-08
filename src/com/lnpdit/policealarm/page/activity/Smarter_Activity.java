package com.lnpdit.policealarm.page.activity;


import java.util.ArrayList;

import com.lnpdit.policealarm.R;
import com.lnpdit.policealarm.page.activity.tab.ExpertFragment;
import com.lnpdit.policealarm.page.activity.tab.GuestFragment;
import com.lnpdit.policealarm.page.activity.tab.TabSwipPager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.widget.LinearLayout;

public class Smarter_Activity extends FragmentActivity{

	private LinearLayout llTabSwipPager;
	private TabSwipPager tabSwipPager;

	private ArrayList<Fragment> fragmentsList;
	private String[] tags;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_smarter);
		initView();
	}

	private void initView() {
		initData();

		llTabSwipPager = (LinearLayout) findViewById(R.id.llTabSwipPager);

		tabSwipPager = new TabSwipPager(getApplicationContext(),
				getSupportFragmentManager(), llTabSwipPager);
		if (!tabSwipPager.setFragmentList(fragmentsList, tags)) {
			System.out.println("初始化失败");
			finish();
		}
	}

	private void initData() {
		fragmentsList = new ArrayList<Fragment>();
		fragmentsList.add(new GuestFragment());
		fragmentsList.add(new ExpertFragment());

		tags = new String[] { "未处理案件", "已处理案件" };

	}
}
