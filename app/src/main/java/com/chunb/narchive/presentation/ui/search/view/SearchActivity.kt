package com.chunb.narchive.presentation.ui.search.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.chunb.narchive.R
import com.chunb.narchive.data.remote.response.Book
import com.chunb.narchive.data.remote.response.Movie
import com.chunb.narchive.databinding.ActivitySearchBinding
import com.chunb.narchive.presentation.ui.search.adapter.BookSearchAdapter
import com.chunb.narchive.presentation.ui.search.adapter.MovieSearchAdapter
import com.chunb.narchive.presentation.ui.search.viewmodel.SearchViewModel
import com.chunb.narchive.presentation.ui.write.view.WriteActivity
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
    private val movieSearchAdapter by lazy {
        MovieSearchAdapter()
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
            bookSearchAdapter.bookClickedListener(object : BookSearchAdapter.BookClickedListener {
                override fun bookClickedListener(view: View, item: Book) {
                    viewModel.setSelectBook(item)
                    setBookDataToWrite(true)
                }

            })
        } else {
            binding.searchRvBook.adapter = movieSearchAdapter
            movieSearchAdapter.movieClickedListener(object : MovieSearchAdapter.MovieClickedListener {
                override fun movieClickedListener(view: View, item: Movie) {
                    viewModel.setSelectMovie(item)
                    setBookDataToWrite(false)
                }

            })
        }
    }

    fun scrollToTop() {
        binding.searchRvBook.scrollToPosition(0)
    }

    fun startSearch() {
        binding.searchRvBook.scrollToPosition(0)
        if(viewModel.searchType.value == true) {
            observeBookData()
        } else {
            observeMovieData()
        }
    }

    private fun observeBookData() {
        lifecycleScope.launch {
            viewModel.getBookList().collectLatest {
                bookSearchAdapter.submitData(it)
            }
        }
    }

    private fun observeMovieData() {
        lifecycleScope.launch {
            viewModel.getMovieList().collectLatest {
                movieSearchAdapter.submitData(it)
            }
        }
    }

    private fun setBookDataToWrite(isBook : Boolean) {
        val intent = Intent(this, WriteActivity::class.java)
        val RESULT_CODE = if(isBook) {
            intent.putExtra("Book", viewModel.selectBook.value)
            1001
        } else {
            intent.putExtra("Movie", viewModel.selectMovie.value)
            1002
        }
        this.setResult(RESULT_CODE, intent)
        finish()
    }

}