package com.jesustrejo10.infinitescrollexample.data

import com.jesustrejo10.infinitescrollexample.model.Response
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.ArrayList

/**
 * @author Jesus Trejo on 4/21/19.
 */
interface EndPoints {

	@GET("/api")
	fun getPeople(@Query("page") page :String, @Query("results") results: String ) : Observable<Response>
}