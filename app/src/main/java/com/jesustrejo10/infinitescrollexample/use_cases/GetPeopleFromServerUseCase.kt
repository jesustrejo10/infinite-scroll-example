package com.jesustrejo10.infinitescrollexample.use_cases

import com.jesustrejo10.infinitescrollexample.data.RetroBase
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

/**
 * @author Jesus Trejo on 4/21/19.
 */
class GetPeopleFromServerUseCase(private val page : String,
                                 private val aboveLayer : GetPeopleFromServerUseCaseContract){

	fun invoke(schedulers : Scheduler){


		RetroBase.create().getPeople(page,"20")
			.subscribeOn(Schedulers.computation())
			.observeOn(schedulers)
			.subscribe(
				{ result -> aboveLayer.manageResponse(result.people) },
				{ _ -> aboveLayer.manageError() }
			)
	}

	interface GetPeopleFromServerUseCaseContract{
		/**
		 * This method send the response that the app gets from the server to the presenter.
		 */
		fun manageResponse ( result : ArrayList<com.jesustrejo10.infinitescrollexample.model.Person>)

		/**
		 * This method said to the above layer that an error occur when the app tries to get the people.
		 */
		fun manageError()
	}
}