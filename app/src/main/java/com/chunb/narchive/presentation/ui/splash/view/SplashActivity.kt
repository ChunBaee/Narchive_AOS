package com.chunb.narchive.presentation.ui.splash.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.chunb.narchive.R
import com.chunb.narchive.presentation.ui.auth.view.SignInActivity
import com.chunb.narchive.presentation.ui.main.view.MainActivity
import com.chunb.narchive.presentation.ui.splash.viewmodel.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.lang.Thread.sleep
@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    private val viewModel : SplashViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_splash)
        observeAutoAuth()
    }

    private fun observeAutoAuth() {
        viewModel.isAuthSuccess.observe(this) {
            if(it == "FAIL") {
                startActivity(Intent(this, SignInActivity::class.java))
            } else {
                startActivity(Intent(this, MainActivity::class.java))
            }
            finish()
        }
    }

    override fun onPause() {
        super.onPause()
        overridePendingTransition(0, 0)
    }
}