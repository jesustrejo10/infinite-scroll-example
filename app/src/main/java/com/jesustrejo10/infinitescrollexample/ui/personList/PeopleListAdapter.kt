package com.jesustrejo10.infinitescrollexample.ui.personList

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.jesustrejo10.infinitescrollexample.R
import com.jesustrejo10.infinitescrollexample.model.Person
import com.squareup.picasso.Picasso

/**
 * @author Jesus Trejo on 4/21/19.
 */
class PeopleListAdapter(var info : ArrayList<Person>) : RecyclerView.Adapter<PeopleListAdapter.ViewHolder>() {

	private lateinit var bottomListener : OnBottomReachedListener

	override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
		val view = LayoutInflater.from(p0.context).inflate(R.layout.recyclerview_row, p0, false)
		return ViewHolder(view)
	}

	override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
		val personToShow = info[position]
		viewHolder.showInfo(personToShow)

		if(position == info.size-1)
			bottomListener.onBottomReached()
	}

	override fun getItemCount(): Int {
		return info.size
	}

	fun setOnBottomReachedListener(listener : OnBottomReachedListener){
		this.bottomListener = listener
	}

	open class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) , View.OnClickListener {

		private var personImage : ImageView = itemView.findViewById(R.id.personImage)
		private var personName : TextView = itemView.findViewById(R.id.personNameTextView)
		private var personLocation : TextView = itemView.findViewById(R.id.personLocation)


		override fun onClick(p0: View?) {
			TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
		}

		fun showInfo(personToShow: Person) {

			Picasso.get().load(personToShow.picture.thumbnail).into(personImage)
			personName.text = personToShow.name.name
			val locationToShow = personToShow.location.state+", "+personToShow.location.city+", "+personToShow.location.postcode
			personLocation.text = locationToShow
		}

	}

	interface OnBottomReachedListener{

		/**
		 * This function is triggered when the user reach the bottom of the screen.
		 */
		fun onBottomReached()

	}

}