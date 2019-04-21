package com.jesustrejo10.infinitescrollexample.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

/**
 * @author Jesus Trejo on 4/21/19.
 */
class RetroBase {

	companion object {
		fun create(): EndPoints {


			val retrofit = Retrofit.Builder()
				.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
				.addConverterFactory(GsonConverterFactory.create())
				.baseUrl("https://randomuser.me")
				.build()

			return retrofit.create(EndPoints::class.java)
		}
	}
}