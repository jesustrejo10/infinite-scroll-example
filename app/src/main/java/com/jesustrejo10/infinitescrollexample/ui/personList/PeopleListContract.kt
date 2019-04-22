package com.jesustrejo10.infinitescrollexample.ui.personList

import com.jesustrejo10.infinitescrollexample.model.Person
import io.reactivex.Scheduler

/**
 * @author Jesus Trejo on 4/21/19.
 */
abstract class PeopleListContract {

	interface View{

		/**
		 * This method send to the View the list with the people that should be showed in the view
		 */
		fun showPeopleInList(people: ArrayList<Person>)

		/**
		 * This method show an error in the entire view.
		 */
		fun showEmptyErrorMessage()

		/**
		 * This method shows a Toast error message
		 */
		fun showErrorMessage()

		/**
		 * This method return an instance of the main Thread of the android app.
		 */
		fun getScheduler(): Scheduler

		/**
		 * This method will show a Toast saying than the entered filter doesn't get any response.
		 */
		fun showNoFilterResult()


	}

	interface Presenter{
		/**
		 * This function will generate an HTTP request to the server to get the people list,
		 * Also it will concat the old list with the new ones.
		 */
		fun getPeople()

		/**
		 * This method will get all the people that names contains the sent character.
		 */
		fun filterByText(p0: String)

	}


}