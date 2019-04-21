package com.jesustrejo10.infinitescrollexample.ui.personList

import com.jesustrejo10.infinitescrollexample.model.Person
import com.jesustrejo10.infinitescrollexample.use_cases.GetPeopleFromServerUseCase

/**
 * @author Jesus Trejo on 4/21/19.
 */
class PeopleListPresenter(val view : PeopleListContract.View) : PeopleListContract.Presenter, GetPeopleFromServerUseCase.GetPeopleFromServerUseCaseContract {


	private var page = 1;
	private var peopleInList = ArrayList<Person>()
	/**
	 * This function will generate an HTTP request to the server to get the people list,
	 * Also it will concat the old list with the new ones.
	 */
	override fun getPeople() {

		GetPeopleFromServerUseCase(page.toString(),this).invoke(view.getScheduler())
		page++
	}

	/**
	 * This method send the response that the app gets from the server to the presenter.
	 */
	override fun manageResponse(result: ArrayList<Person>) {
		if(page == 2)
			peopleInList = result
		else
			peopleInList.addAll(result)

		view.showPeopleInList(peopleInList)
	}

	/**
	 * This method said to the above layer that an error occur when the app tries to get the people.
	 */
	override fun manageError() {
		page--
		if(peopleInList.isEmpty())
			view.showEmptyErrorMessage()
		else
			view.showErrorMessage()
	}

}