package com.jesustrejo10.infinitescrollexample.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * @author Jesus Trejo on 4/21/19.
 */
class ResponseInfo(

	@SerializedName("page")
	@Expose
	val page : Int

)