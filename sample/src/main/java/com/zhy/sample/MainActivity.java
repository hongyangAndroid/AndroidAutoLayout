package com.zhy.sample;

import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.WindowManager;

import com.zhy.autolayout.AutoLayoutActivity;
import com.zhy.sample.fragment.ListFragment;
import com.zhy.sample.fragment.PayFragment;
import com.zhy.sample.fragment.RecyclerViewFragment;
import com.zhy.sample.fragment.RegisterFragment;

import java.util.ArrayList;

public class MainActivity extends AutoLayoutActivity
{

	private ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		setImmersionStatus();
		setContentView(R.layout.activity_main);


		initView();
		initDatas();
	}

	private void setImmersionStatus() {
		if (VERSION.SDK_INT >= VERSION_CODES.KITKAT) {
			// 透明状态栏
			getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
			// 透明导航栏
			getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
		}
	}

	private void initDatas() {
		ArrayList<Fragment> mList = new ArrayList<Fragment>();
		mList.add(new ListFragment());
		mList.add(new RegisterFragment());
		mList.add(new PayFragment());
		mList.add(new RecyclerViewFragment());
		mViewPager.setAdapter(new MyAdapter(getSupportFragmentManager(), mList));
	}

	private void initView() {
		mViewPager = (ViewPager) findViewById(R.id.id_viewpager);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}


	public class MyAdapter extends FragmentPagerAdapter {
		ArrayList<Fragment> tabs = null;

		public MyAdapter(FragmentManager fm, ArrayList<Fragment> tabs) {
			super(fm);
			this.tabs = tabs;
		}

		@Override
		public Fragment getItem(int pos) {
			return tabs.get(pos);
		}

		@Override
		public int getCount() {
			return tabs.size();
		}
	}

}
