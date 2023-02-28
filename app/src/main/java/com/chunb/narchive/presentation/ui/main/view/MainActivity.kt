package com.chunb.narchive.presentation.ui.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.chunb.narchive.R
import com.chunb.narchive.databinding.ActivityMainBinding
import com.chunb.narchive.presentation.ui.main.archive.view.ArchiveFragment
import com.chunb.narchive.presentation.ui.main.feed.view.FeedFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initBottomNav()
        initBottomReselect()
    }

    private fun initBottomNav() {
        binding.mainLayoutBtmNav.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.main_btm_home -> {
                    supportFragmentManager.beginTransaction().replace(R.id.main_layout_container, FeedFragment()).commit()
                }
                R.id.main_btm_activates -> {
                    supportFragmentManager.beginTransaction().replace(R.id.main_layout_container, ArchiveFragment()).commit()
                }
                R.id.main_btm_settings -> {
                    //supportFragmentManager.beginTransaction().replace(R.id.main_layout_container, SettingFragment()).commit()
                }
            }
            return@setOnItemSelectedListener true
        }
        binding.mainLayoutBtmNav.selectedItemId = R.id.main_btm_home
    }

    private fun initBottomReselect() {
        binding.mainLayoutBtmNav.setOnItemReselectedListener {
            when(it.itemId) {
                R.id.main_btm_home -> {}
            }
        }
    }
}