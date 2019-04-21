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
import com.jesustrejo10.infinitescrollexample.model.Person
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_person_list.*


/**
 * @author Jesus Trejo on 4/19/19.
 */
class PeopleListFragment : Fragment() , PeopleListContract.View, PeopleListAdapter.OnBottomReachedListener{

	private var firstDataEntry = true
	private lateinit var adapter : PeopleListAdapter

	/**
	 * This method send to the View the list with the people that should be showed in the view
	 */
	override fun showPeopleInList(people: ArrayList<Person>) {
		swipeRefreshLayout.isRefreshing=false

		if(firstDataEntry){
			firstDataEntry = false
			adapter = PeopleListAdapter(people)
			board_topic_list.layoutManager = LinearLayoutManager(context)
			adapter.setOnBottomReachedListener(this)
			board_topic_list.adapter = adapter

			swipeRefreshLayout.setOnRefreshListener({
				// do something

				// after refresh is done, remember to call the following code

				if (swipeRefreshLayout != null && swipeRefreshLayout.isRefreshing()) {
					swipeRefreshLayout.setRefreshing(false)  // This hides the spinner
				}

			})
		}else{
			adapter.notifyDataSetChanged()
		}


	}

	/**
	 * This method show an error in the entire view.
	 */
	override fun showEmptyErrorMessage() {
		swipeRefreshLayout.isRefreshing=false

		println("holamundo")
	}

	/**
	 * This method shows a Toast error message
	 */
	override fun showErrorMessage() {
		swipeRefreshLayout.isRefreshing=false

		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}

	private lateinit var mScrollListener : EndlessRecyclerOnScrollListener
	private lateinit var presenter : PeopleListContract.Presenter

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		val rootView = inflater.inflate(R.layout.fragment_person_list, container, false)
		return rootView
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		presenter = PeopleListPresenter(this)
		requestPeople()
	}

	/**
	 * This function is triggered when the user reach the bottom of the screen.
	 */
	override fun onBottomReached() {
		swipeRefreshLayout.isRefreshing=true
		presenter.getPeople()
	}

	/**
	 * This method return an instance of the main Thread of the android app.
	 */
	override fun getScheduler(): Scheduler {
		return AndroidSchedulers.mainThread()
	}


	private fun requestPeople() {
		presenter.getPeople()
	}

}