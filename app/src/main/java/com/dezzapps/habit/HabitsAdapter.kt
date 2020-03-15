package com.dezzapps.habit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item.view.*

class HabitsAdapter(val habits: List<Habit>): RecyclerView.Adapter<HabitViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item,  parent,false)

        return  HabitViewHolder(view)

    }

//    override fun getItemCount(): Int {
//        return  habits.size
//    }

    override fun getItemCount() = habits.size


    override fun onBindViewHolder(holder: HabitViewHolder, position: Int) {

        holder.bind(habit = habits[position])

    }
}