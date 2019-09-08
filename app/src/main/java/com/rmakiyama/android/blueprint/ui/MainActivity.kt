package com.rmakiyama.android.blueprint.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rmakiyama.android.blueprint.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
