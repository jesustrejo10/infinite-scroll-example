package com.jesustrejo10.infinitescrollexample.ui

import android.content.res.ColorStateList
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.content.ContextCompat
import android.support.v4.widget.ImageViewCompat
import android.support.v7.app.AppCompatActivity
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import com.jesustrejo10.infinitescrollexample.R
import kotlinx.android.synthetic.main.activity_tab.*
import kotlinx.android.synthetic.main.fragment_tab.view.*

class TabActivity : AppCompatActivity() {

	/**
	 * The [android.support.v4.view.PagerAdapter] that will provide
	 * fragments for each of the sections. We use a
	 * {@link FragmentPagerAdapter} derivative, which will keep every
	 * loaded fragment in memory. If this becomes too memory intensive, it
	 * may be best to switch to a
	 * [android.support.v4.app.FragmentStatePagerAdapter].
	 */
	private var mSectionsPagerAdapter: SectionsPagerAdapter? = null

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_tab)
		// Create the adapter that will return a fragment for each of the three
		// primary sections of the activity.
		mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)

		// Set up the ViewPager with the sections adapter.
		container.adapter = mSectionsPagerAdapter
		configureTabLayout()

	}


	private fun configureTabLayout() {

		tabLayout.addTab(tabLayout.newTab().setCustomView(R.layout.tab_home_layout))
		tabLayout.addTab(tabLayout.newTab().setCustomView(R.layout.tab_explore_layout))
		tabLayout.addTab(tabLayout.newTab().setCustomView(R.layout.tab_queues_layout))
		tabLayout.addTab(tabLayout.newTab().setCustomView(R.layout.tab_profile_layout))


		val adapter = TabPagerAdapter(supportFragmentManager,
			tabLayout.tabCount)
		container.adapter = adapter

		container.addOnPageChangeListener(
			TabLayout.TabLayoutOnPageChangeListener(tabLayout))
		tabLayout.addOnTabSelectedListener(object :
			TabLayout.OnTabSelectedListener {
			override fun onTabSelected(tab: TabLayout.Tab) {
				container.currentItem = tab.position
				activateTab(tab.position, tabLayout)

			}

			override fun onTabUnselected(tab: TabLayout.Tab) {

			}

			override fun onTabReselected(tab: TabLayout.Tab) {

			}

		})
	}

	private fun activateTab(position: Int, tabLayout: TabLayout?) {
		if(tabLayout!= null){
			when (position){
				0->{
					inactivateTabOption(1,tabLayout)
					inactivateTabOption(2,tabLayout)
					inactivateTabOption(3,tabLayout)
					activateTabOption(0,tabLayout)
				}

				1 -> {
					inactivateTabOption(0,tabLayout)
					inactivateTabOption(2,tabLayout)
					inactivateTabOption(3,tabLayout)
					activateTabOption(1,tabLayout)
				}

				2-> {
					inactivateTabOption(0,tabLayout)
					inactivateTabOption(1,tabLayout)
					inactivateTabOption(3,tabLayout)
					activateTabOption(2,tabLayout)
				}

				3-> {
					inactivateTabOption(0,tabLayout)
					inactivateTabOption(1,tabLayout)
					inactivateTabOption(2,tabLayout)
					activateTabOption(3,tabLayout)
				}
			}

		}
	}

	private fun inactivateTabOption(position: Int, tabLayout: TabLayout?){
		val tab = tabLayout!!.getTabAt(position)
		val view = tab!!.customView
		val icon = view!!.findViewById<ImageView>(R.id.tabIcon)
		ImageViewCompat.setImageTintList(icon, ColorStateList.valueOf(ContextCompat.getColor(this, R.color.small_text_color)))

		val text = view.findViewById<TextView>(R.id.tabText)
		text.setTextColor(ContextCompat.getColor(this, R.color.small_text_color))
	}

	private fun activateTabOption(position: Int, tabLayout: TabLayout?){
		val tab = tabLayout!!.getTabAt(position)
		val view = tab!!.customView
		val icon = view!!.findViewById<ImageView>(R.id.tabIcon)

		ImageViewCompat.setImageTintList(icon, ColorStateList.valueOf(ContextCompat.getColor(this, R.color.active_menu)))
		val text = view.findViewById<TextView>(R.id.tabText)
		text.setTextColor(ContextCompat.getColor(this, R.color.active_menu))
	}

	override fun onCreateOptionsMenu(menu: Menu): Boolean {
		// Inflate the menu; this adds items to the action bar if it is present.
		menuInflater.inflate(R.menu.menu_tab, menu)
		return true
	}

	override fun onOptionsItemSelected(item: MenuItem): Boolean {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		val id = item.itemId

		if (id == R.id.action_settings) {
			return true
		}

		return super.onOptionsItemSelected(item)
	}


	/**
	 * A [FragmentPagerAdapter] that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	inner class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

		override fun getItem(position: Int): Fragment {
			// getItem is called to instantiate the fragment for the given page.
			// Return a PlaceholderFragment (defined as a static inner class below).
			return PlaceholderFragment.newInstance(position + 1)
		}

		override fun getCount(): Int {
			// Show 3 total pages.
			return 3
		}
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	class PlaceholderFragment : Fragment() {

		override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
		                          savedInstanceState: Bundle?): View? {
			val rootView = inflater.inflate(R.layout.fragment_tab, container, false)
			rootView.section_label.text = getString(R.string.section_format, arguments!!.getInt(ARG_SECTION_NUMBER))
			return rootView
		}

		companion object {
			/**
			 * The fragment argument representing the section number for this
			 * fragment.
			 */
			private val ARG_SECTION_NUMBER = "section_number"

			/**
			 * Returns a new instance of this fragment for the given section
			 * number.
			 */
			fun newInstance(sectionNumber: Int): PlaceholderFragment {
				val fragment = PlaceholderFragment()
				val args = Bundle()
				args.putInt(ARG_SECTION_NUMBER, sectionNumber)
				fragment.arguments = args
				return fragment
			}
		}
	}
}
