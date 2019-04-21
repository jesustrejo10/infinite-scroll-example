package com.jesustrejo10.infinitescrollexample.ui.personList

import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jesustrejo10.infinitescrollexample.EndlessRecyclerOnScrollListener
import com.jesustrejo10.infinitescrollexample.R
import kotlinx.android.synthetic.main.fragment_person_list.*
import android.support.v4.widget.SwipeRefreshLayout




/**
 * @author Jesus Trejo on 4/19/19.
 */
class PersonListFragment : Fragment(){

	private lateinit var mScrollListener : EndlessRecyclerOnScrollListener

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		val rootView = inflater.inflate(R.layout.fragment_person_list, container, false)
		return rootView
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		manageViewComponents()
	}

	private fun manageViewComponents() {


		val animalNames = ArrayList<String>()
		animalNames.add("Horse")
		animalNames.add("Cow")
		animalNames.add("Camel")
		animalNames.add("Sheep")
		animalNames.add("Goat")
		animalNames.add("Horse")
		animalNames.add("Cow")
		animalNames.add("Camel")
		animalNames.add("Sheep")
		animalNames.add("Goat")
		animalNames.add("Horse")
		animalNames.add("Cow")
		animalNames.add("Camel")
		animalNames.add("Sheep")
		animalNames.add("Goat")
		animalNames.add("Horse")
		animalNames.add("Cow")
		animalNames.add("Camel")
		animalNames.add("Sheep")
		animalNames.add("Goat")
		animalNames.add("Horse")
		animalNames.add("Cow")
		animalNames.add("Camel")
		animalNames.add("Sheep")
		animalNames.add("Goat")
		animalNames.add("Horse")
		animalNames.add("Cow")
		animalNames.add("Camel")
		animalNames.add("Sheep")
		animalNames.add("Goat")
		animalNames.add("Horse")
		animalNames.add("Cow")
		animalNames.add("Camel")
		animalNames.add("Sheep")
		animalNames.add("Goat")
		animalNames.add("Horse")
		animalNames.add("Cow")
		animalNames.add("Camel")
		animalNames.add("Sheep")
		animalNames.add("Goat")
		animalNames.add("Horse")
		animalNames.add("Cow")
		animalNames.add("Camel")
		animalNames.add("Sheep")
		animalNames.add("Goat")
		animalNames.add("Horse")
		animalNames.add("Cow")
		animalNames.add("Camel")
		animalNames.add("Sheep")
		animalNames.add("Goat")
		animalNames.add("Horse")
		animalNames.add("Cow")
		animalNames.add("Camel")
		animalNames.add("Sheep")
		animalNames.add("Goat")
		animalNames.add("Horse")
		animalNames.add("Cow")
		animalNames.add("Camel")
		animalNames.add("Sheep")
		animalNames.add("Goat")
		animalNames.add("Horse")
		animalNames.add("Cow")
		animalNames.add("Camel")
		animalNames.add("Sheep")
		animalNames.add("Goat")
		animalNames.add("Horse")
		animalNames.add("Cow")
		animalNames.add("Camel")
		animalNames.add("Sheep")
		animalNames.add("Goat")
		animalNames.add("Horse")
		animalNames.add("Cow")
		animalNames.add("Camel")
		animalNames.add("Sheep")
		animalNames.add("Goat")








		val adapter = PersonListAdapter(animalNames)
		board_topic_list.layoutManager = LinearLayoutManager(context)
		board_topic_list.adapter = adapter


		mScrollListener = object : EndlessRecyclerOnScrollListener(LinearLayoutManager(context)) {
			override fun onLoadMore(current_page: Int) {
				setLoading(true)
				swipeRefreshLayout.isRefreshing=true
				voidea()
				println("hola")

			}
		}

		board_topic_list.addOnScrollListener(mScrollListener)


		swipeRefreshLayout.setOnRefreshListener({
			// do something

			// after refresh is done, remember to call the following code

			if (swipeRefreshLayout != null && swipeRefreshLayout.isRefreshing()) {
				swipeRefreshLayout.setRefreshing(false)  // This hides the spinner
			}
		})




	}

	private fun voidea() {

		Handler().postDelayed({
			mScrollListener.setLoading(false)
			swipeRefreshLayout.isRefreshing=false
		}, 2000)


		// after loading is done, please call the following method to re-enable onLoadMore
		// usually it should be called in onCompleted() method
	}
}