package com.dezzapps.habit.db

import android.provider.BaseColumns

val DATABASE_NAME = "habits.db"
val DATABASE_VERSION = 1

object HabitEntry: BaseColumns {
    val TABLE_NAME = "habits"
    val _ID = "id"
    val TITLE_COL = "title"
    val DESCRIP_COL = "description"
    val IMAGE_COL = "image"
}