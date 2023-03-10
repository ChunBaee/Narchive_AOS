package com.chunb.narchive.presentation.ui.filter.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.chunb.narchive.R
import com.chunb.narchive.databinding.ActivityFilterBinding
import com.chunb.narchive.presentation.ui.detail.view.DetailActivity
import com.chunb.narchive.presentation.ui.filter.adapter.FilterFeedAdapter
import com.chunb.narchive.presentation.ui.filter.viewmodel.FilterViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FilterActivity : AppCompatActivity() {
    private lateinit var binding : ActivityFilterBinding
    private val viewModel : FilterViewModel by viewModels()
    private lateinit var filterAdapter : FilterFeedAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    fun filterAdapter() = FilterFeedAdapter(::openDetailFeedActivity).also { filterAdapter = it }

    private fun initView() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_filter)
        binding.viewModel = viewModel
        binding.activity = this
    }

    fun initObserve() {
        lifecycleScope.launch {
            viewModel.findWithFilter().collectLatest {
                filterAdapter.submitData(it)
            }
        }
    }

    private fun openDetailFeedActivity(position : Int) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("contentIdx", position)
        startActivity(intent)
    }

    fun dismiss() {
        finish()
    }

}