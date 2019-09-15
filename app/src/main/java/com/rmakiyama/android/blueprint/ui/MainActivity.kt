package com.rmakiyama.android.blueprint.ui

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.rmakiyama.android.blueprint.R
import com.rmakiyama.android.blueprint.databinding.ActivityMainBinding
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupFullScreen(binding.root)
    }
}
