package com.nerdymykl.luckmeasure

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun goToMeasureActivity(view: View) {
        val intent = Intent(this, MeasureLuckActivity::class.java)
        startActivity(intent)
    }

}