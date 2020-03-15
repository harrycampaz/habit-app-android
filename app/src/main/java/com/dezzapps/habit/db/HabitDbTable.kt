package com.dezzapps.habit.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import com.dezzapps.habit.Habit
import java.io.ByteArrayOutputStream

class HabitDbTable(context: Context) {

    private val TAG = HabitDbTable::class.java.simpleName

    private val dbHelper = HabitTrainerDb(context)

    fun store(habit: Habit): Long{
        val db = dbHelper.writableDatabase

        val values = ContentValues()
        with(values) {
            put(HabitEntry.TITLE_COL, habit.title)
            put(HabitEntry.DESCRIP_COL, habit.description)
            put(HabitEntry.IMAGE_COL, toByteArray(habit.image))
        }
        val id: Long = db.transaction {
           insert(HabitEntry.TABLE_NAME, null, values)
        }

        Log.d(TAG, "Store new habit")
        return  id
    }

    fun readAllHabits(): List<Habit> {

        val  columns = arrayOf(HabitEntry._ID, HabitEntry.TITLE_COL, HabitEntry.DESCRIP_COL, HabitEntry.IMAGE_COL)

        val order = "${HabitEntry._ID} ASC"

        val db = dbHelper.readableDatabase

       val cursor = db.doQuery(HabitEntry.TABLE_NAME, columns, orderBy =  order)

        return  parseHabitsFrom(cursor)

    }

    private fun parseHabitsFrom(cursor: Cursor): MutableList<Habit> {
        val habits = mutableListOf<Habit>()
        while (cursor.moveToNext()) {
            val title = cursor.getMyString(HabitEntry.TITLE_COL)
            val descrip = cursor.getMyString(HabitEntry.DESCRIP_COL)
            val bitmap = cursor.getBitmap(HabitEntry.IMAGE_COL)



            habits.add(Habit(title, descrip, bitmap))

        }
        cursor.close()
        return habits
    }

    private fun toByteArray(image: Bitmap): ByteArray {

        val stream = ByteArrayOutputStream()
        image.compress(Bitmap.CompressFormat.PNG, 0 , stream)

        return stream.toByteArray()

    }
}

private fun Cursor.getMyString(columnName: String): String = getString(getColumnIndex(columnName))

private fun Cursor.getBitmap(columnName: String): Bitmap {
    val byte = getBlob(getColumnIndex(columnName))
    return BitmapFactory.decodeByteArray(byte, 0 , byte.size)
}

private inline fun SQLiteDatabase.doQuery(table: String, columns: Array<String>, selection: String?=  null,
                                   selectionArgs: Array<String>?=  null, groupBy: String?=  null, having: String?=  null, orderBy: String?=  null ): Cursor{

    return  query(table, columns, selection, selectionArgs,  groupBy, having, orderBy)
}

private inline fun<T> SQLiteDatabase.transaction(function: SQLiteDatabase.() -> T) : T{
    beginTransaction()
    val result = try {
        val returnValue = function()
        setTransactionSuccessful()
        returnValue
    }finally {
        endTransaction()
    }
    close()
    return result
}