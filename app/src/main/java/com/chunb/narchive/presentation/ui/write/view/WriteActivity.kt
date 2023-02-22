package com.chunb.narchive.presentation.ui.write.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.chunb.narchive.R
import com.chunb.narchive.databinding.ActivityWriteBinding
import com.chunb.narchive.presentation.ui.write.viewmodel.WriteViewModel
import com.chunb.narchive.presentation.ui.write.write.view.WriteFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WriteActivity : AppCompatActivity() {
    private lateinit var binding : ActivityWriteBinding
    private val viewModel : WriteViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initBinding()
    }

    private fun initBinding() {
        binding.lifecycleOwner = this
    }

    override fun onStart() {
        super.onStart()
        supportFragmentManager.beginTransaction().replace(R.id.write_layout_container, WriteFragment()).commit()
    }

}