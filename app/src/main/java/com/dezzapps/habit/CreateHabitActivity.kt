package com.dezzapps.habit

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_createhabit.*
import java.io.IOException
import kotlin.math.log

class CreateHabitActivity : AppCompatActivity() {

    private val TAG = CreateHabitActivity::class.java.simpleName

    private val CHOOSE_IMAGE_REQUEST = 1
    private var imageBitmap: Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_createhabit)
    }

    fun chooseImage(view: View) {
        val intent = Intent()

        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT

        val chooser =  Intent.createChooser(intent, "Choose image for habit")
        startActivityForResult(chooser, CHOOSE_IMAGE_REQUEST)

        Log.d(TAG,"Intennto ")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // Check which request we're responding to
        if (requestCode == CHOOSE_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.data != null) {

            Log.d(TAG, "An imagen was choose")
            
            val bitmap = tryReadBitmap(data.data!!)

            bitmap?.let {
                this.imageBitmap = bitmap
                iv_image.setImageBitmap(bitmap)
                Log.d(TAG, "Read Image")
            }

        }
    }

    private fun tryReadBitmap(data: Uri): Bitmap? {

       return try {

            MediaStore.Images.Media.getBitmap(contentResolver, data)


        }catch (e: IOException){
            e.printStackTrace()
            null
        }

    }

    fun storeHabit(view: View) {
        if(et_title.isBlank() || et_descrip.isBlank()){
            Log.d(TAG, "No habit stored, title and description are required ")
            displayErrorMsg("title and description are required")
            return
        }else if(imageBitmap == null) {
            Log.d(TAG, "No habit stored, image missig")
            displayErrorMsg("Image is required")
            return
        }

        tv_error.visibility = View.INVISIBLE

    }

    private fun displayErrorMsg(s: String) {

        tv_error.text = s
        tv_error.visibility = View.VISIBLE

    }

}

private fun EditText.isBlank() = this.text.toString().isBlank()

