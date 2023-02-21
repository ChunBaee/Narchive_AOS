package com.chunb.narchive.presentation.ui.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.chunb.narchive.R
import com.chunb.narchive.databinding.ActivityMainBinding
import com.chunb.narchive.presentation.ui.main.feed.view.FeedFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    override fun onStart() {
        super.onStart()
        supportFragmentManager.beginTransaction().replace(R.id.main_layout_container, FeedFragment()).commit()
    }
}