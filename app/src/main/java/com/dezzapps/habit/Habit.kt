package com.dezzapps.habit

import android.graphics.Bitmap

data class Habit(val title: String, val description: String, val image: Bitmap)

//fun getSampleHabits(): List<Habit>{
//    return listOf(
//        Habit("Go for a walk", "A nice walk in the sun ...", R.drawable.walk),
//        Habit("Drink a glass a water", "A refreshing glass of water ...", R.drawable.water)
//    )
//}