package com.jesustrejo10.infinitescrollexample.ui.personList

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jesustrejo10.infinitescrollexample.R
import com.jesustrejo10.infinitescrollexample.model.Person

/**
 * @author Jesus Trejo on 4/21/19.
 */
class PeopleListAdapter(var info : ArrayList<Person>) : RecyclerView.Adapter<PeopleListAdapter.ViewHolder>() {



	override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
		val view = LayoutInflater.from(p0.context).inflate(R.layout.recyclerview_row, p0, false)
		return ViewHolder(view)
	}

	override fun onBindViewHolder(p0: ViewHolder, p1: Int) {



	}

	override fun getItemCount(): Int {
		return info.size
	}


	open class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) , View.OnClickListener {

		override fun onClick(p0: View?) {
			TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
		}

	}

}