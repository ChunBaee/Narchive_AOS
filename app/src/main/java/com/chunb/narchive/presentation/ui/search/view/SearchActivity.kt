package com.chunb.narchive.presentation.ui.search.view

import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.chunb.narchive.R
import com.chunb.narchive.databinding.ActivitySearchBinding
import com.chunb.narchive.presentation.ui.search.adapter.BookSearchAdapter
import com.chunb.narchive.presentation.ui.search.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchBinding
    private val viewModel: SearchViewModel by viewModels()
    private val bookSearchAdapter by lazy {
        BookSearchAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search)

        initBinding()
    }

    private fun initBinding() {
        binding.lifecycleOwner = this
        binding.activity = this
        binding.viewModel = viewModel
    }

    override fun onStart() {
        super.onStart()
        if (intent != null) {
            viewModel.setSearchType(intent.getBooleanExtra("isBook", false))
        }
        initSearchRv()
    }

    private fun initSearchRv() {
        if (viewModel.searchType.value == true) {
            binding.searchRvBook.adapter = bookSearchAdapter
        }
    }

    fun scrollToTop() {
        binding.searchRvBook.scrollToPosition(0)
    }

    fun startSearch() {
        binding.searchRvBook.scrollToPosition(0)
        if(viewModel.searchType.value == true) {
            observeBookData()
        }
    }

    private fun observeBookData() {
        lifecycleScope.launch {
            viewModel.getBookList().collectLatest {
                Log.d("----", "observeBookData: $it")
                bookSearchAdapter.submitData(it)
            }
        }
    }

}