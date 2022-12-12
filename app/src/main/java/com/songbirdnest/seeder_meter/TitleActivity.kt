package com.songbirdnest.seeder_meter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import androidx.constraintlayout.widget.ConstraintSet.Motion
import androidx.core.view.GestureDetectorCompat

class TitleActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_title)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event?.action == MotionEvent.ACTION_UP){
            loadNextSelection()
        }
        return super.onTouchEvent(event)
    }
    fun loadNextSelection(){
        val intent = Intent(this,ReadingsActivity::class.java)
        startActivity(intent)
    }
}