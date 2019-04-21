package com.jesustrejo10.infinitescrollexample.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * @author Jesus Trejo on 4/21/19.
 */
class Location(

	@SerializedName("state")
	@Expose
	var state: String,

	@SerializedName("city")
	@Expose
	var city: String,

	@SerializedName("postcode")
	@Expose
	var postcode: String

)
