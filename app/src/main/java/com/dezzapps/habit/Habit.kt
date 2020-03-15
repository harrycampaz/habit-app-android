package com.dezzapps.habit

data class Habit(val title: String, val description: String, val image: Int)

fun getSampleHabits(): List<Habit>{
    return listOf(
        Habit("Go for a walk", "A nice walk in the sun ...", R.drawable.walk),
        Habit("Drink a glass a water", "A refreshing glass of water ...", R.drawable.water)
    )
}