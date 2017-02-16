package com.ding.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.List;

public class MyPagerAdapter extends FragmentPagerAdapter {
	private List<String> titles = null;
	private List<Fragment> fragments = null;

	public MyPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	public MyPagerAdapter(FragmentManager fm, List<String> titles,
						  List<Fragment> fragments) {
		super(fm);
		this.titles = titles;
		this.fragments = fragments;
	}

	@Override
	public int getCount() {
		return titles.size();
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return titles.get(position % titles.size()).toUpperCase();
	}

	@Override
	public Fragment getItem(int position) {
		Bundle bundle = new Bundle();
		bundle.putInt("position", position);
		Fragment fragment = fragments.get(position);
		fragment.setArguments(bundle);
		return fragment;
	}

	// 这个方法主要判断是否保存过fragment如果没保存过直接调用getItem的方法创建一个,看父的构造方法就知道了
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		// TODO Auto-generated method stub
		return super.instantiateItem(container, position);
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		// TODO Auto-generated method stub
		// super.destroyItem(container, position, object);
		// 这个方法是一直在重复创建新的fragment,直接注视掉父类的构造方法就可以了,有兴趣的可以打开注释看下打印的生命周期的值
	}
}
