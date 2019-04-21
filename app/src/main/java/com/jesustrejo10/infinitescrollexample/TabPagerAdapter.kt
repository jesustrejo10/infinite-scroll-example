package com.jesustrejo10.infinitescrollexample

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.jesustrejo10.infinitescrollexample.personList.PersonListFragment

/**
 * @author Jesus Trejo on 4/19/19.
 */
class TabPagerAdapter(fm: FragmentManager, private var tabCount: Int) :
	FragmentPagerAdapter(fm) {

	override fun getItem(position: Int): Fragment? {

		when (position) {
			0 -> return PersonListFragment()
			1 -> return TabActivity.PlaceholderFragment.newInstance(2)
			2 -> return TabActivity.PlaceholderFragment.newInstance(3)
			3 -> return TabActivity.PlaceholderFragment.newInstance(4)
			else -> return null
		}
	}

	override fun getCount(): Int {
		return tabCount
	}
}