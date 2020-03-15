package com.dezzapps.habit

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item.view.*

class HabitViewHolder(val view: View): RecyclerView.ViewHolder(view) {

    fun bind(habit: Habit){

        itemView.tv_title.text = habit.title
        itemView.tv_description.text = habit.description
        itemView.iv_icon.setImageResource(habit.image)

    }


}
