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
class PeolpleListFragment : Fragment() , PeopleListContract.View{


	/**
	 * This method send to the View the list with the people that should be showed in the view
	 */
	override fun showPeopleInList(people: ArrayList<Person>) {
		val adapter = PeopleListAdapter(people)
		board_topic_list.layoutManager = LinearLayoutManager(context)
		board_topic_list.adapter = adapter


		mScrollListener = object : EndlessRecyclerOnScrollListener(LinearLayoutManager(context)) {
			override fun onLoadMore(current_page: Int) {
				setLoading(true)
				swipeRefreshLayout.isRefreshing=true
				voidea()
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

	/**
	 * This method show an error in the entire view.
	 */
	override fun showEmptyErrorMessage() {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}

	/**
	 * This method shows a Toast error message
	 */
	override fun showErrorMessage() {
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

	private fun requestPeople() {
		presenter.getPeople()
	}

	/**
	 * This method return an instance of the main Thread of the android app.
	 */
	override fun getScheduler(): Scheduler {
		return AndroidSchedulers.mainThread()
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