package com.jesustrejo10.infinitescrollexample.ui.personList

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.jesustrejo10.infinitescrollexample.R
import com.jesustrejo10.infinitescrollexample.model.Person
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_person_list.*


/**
 * @author Jesus Trejo on 4/19/19.
 */
class PeopleListFragment : Fragment() , PeopleListContract.View, PeopleListAdapter.OnBottomReachedListener {

	private var firstDataEntry = true
	private lateinit var adapter : PeopleListAdapter
	private lateinit var presenter : PeopleListContract.Presenter


	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		return inflater.inflate(R.layout.fragment_person_list, container, false)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		presenter = PeopleListPresenter(this)
		requestPeople()
		swipeRefreshLayout.setOnRefreshListener({
			checkIfPeopleAreInScreen()
		})
	}

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

		}else{
			adapter.notifyDataSetChanged()
		}


	}

	/**
	 * This method manage the event if the user pull to refresh the screen,
	 * If he doesn't got the people on the first try, he can try again using that gesture.
	 */
	private fun checkIfPeopleAreInScreen() {
		try {
			if (adapter.info.isEmpty()) {
				presenter.getPeople()
			} else {
				if (swipeRefreshLayout != null && swipeRefreshLayout.isRefreshing()) {
					swipeRefreshLayout.isRefreshing = false
				}
			}
		}catch (e:UninitializedPropertyAccessException){
			presenter.getPeople()
		}
	}

	/**
	 * This method show an error in the entire view.
	 */
	override fun showEmptyErrorMessage() {
		swipeRefreshLayout.isRefreshing=false
	}

	/**
	 * This method shows a Toast error message
	 */
	override fun showErrorMessage() {
		swipeRefreshLayout.isRefreshing=false
		Toast.makeText(context,R.string.error_getting_people,Toast.LENGTH_LONG).show()
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

	/**
	 * This method request to the presenter to get the people from the API
	 */
	private fun requestPeople() {
		presenter.getPeople()
	}

}