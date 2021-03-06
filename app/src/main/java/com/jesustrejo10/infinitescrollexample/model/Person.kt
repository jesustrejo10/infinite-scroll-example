package com.jesustrejo10.infinitescrollexample.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * @author Jesus Trejo on 4/21/19.
 */
class Person(

	@SerializedName("name")
	@Expose
	var name: Name,

	@SerializedName("location")
	@Expose
	var location: Location,

	@SerializedName("picture")
	@Expose
	var picture: Picture

)