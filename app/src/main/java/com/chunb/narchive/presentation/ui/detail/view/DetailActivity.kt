package com.chunb.narchive.presentation.ui.detail.view

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet.Motion
import androidx.databinding.DataBindingUtil
import androidx.databinding.adapters.ViewStubBindingAdapter.setOnInflateListener
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.chunb.narchive.R
import com.chunb.narchive.data.data.Mood
import com.chunb.narchive.data.remote.response.Book
import com.chunb.narchive.data.remote.response.Movie
import com.chunb.narchive.databinding.ActivityDetailBinding
import com.chunb.narchive.databinding.ItemFormBookBinding
import com.chunb.narchive.databinding.ItemFormMovieBinding
import com.chunb.narchive.presentation.ui.comment.view.CommentActivity
import com.chunb.narchive.presentation.ui.detail.adapter.DetailImageAdapter
import com.chunb.narchive.presentation.ui.detail.viewmodel.DetailViewModel
import com.chunb.narchive.presentation.util.changeYFromTouch
import com.chunb.narchive.presentation.util.getCloseDrawerAlphaAnim
import com.chunb.narchive.presentation.util.getCloseDrawerAnim
import com.chunb.narchive.presentation.util.getOpenAnim
import com.chunb.narchive.presentation.util.getOpenDrawerAlphaAnim
import com.chunb.narchive.presentation.util.getOpenDrawerAnim
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private val viewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)

        initBinding()
        observeContentId()
        getContentDetailData()
        observeDetail()
    }

    private fun initBinding() {
        binding.activity = this
    }

    private fun getContentDetailData() {
        val contentId = intent.getIntExtra("contentIdx", 0)
        viewModel.setContentId(contentId)

    }

    private fun observeContentId() {
        viewModel.contentIdx.observe(this) {
            viewModel.getDetailContent(it)
        }
    }

    private fun observeDetail() {
        viewModel.contentData.observe(this) {
            binding.content = it
            binding.mood = Mood.valueOf(it.mood).res
            it.images?.let { images -> setVp(images) }
            if (it.book?.isNotEmpty() == true) setBook(it.book[0])
            if (it.movie?.isNotEmpty() == true) setMovie(it.movie[0])
        }
    }

    private fun setVp(images: List<String>) {
        binding.detailVpImages.apply {
            getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
            adapter = DetailImageAdapter(images)
            TabLayoutMediator(binding.detailTabIndicator, this) { _, _ ->
            }.attach()
        }
    }

    private fun setBook(book: Book) {
        binding.itemMainRvContentsLayoutBook.apply {
            setOnInflateListener { _, _ ->
                val bookBinding = this.binding as ItemFormBookBinding
                bookBinding.bookData = book
            }
        }
        binding.itemMainRvContentsLayoutBook.viewStub?.inflate()
    }

    private fun setMovie(movie: Movie) {
        binding.itemMainRvContentsLayoutMovie.apply {
            setOnInflateListener { _, _ ->
                val movieBinding = this.binding as ItemFormMovieBinding
                movieBinding.movieData = movie
            }
        }
        binding.itemMainRvContentsLayoutMovie.viewStub?.inflate()
    }

    fun openComment() {
        val intent = Intent(this, CommentActivity::class.java)
        intent.putExtra("contentIdx", viewModel.contentIdx.value)
        startActivity(intent)
    }

    fun onBookClicked() {
        binding.itemMainRvContentsLayoutBook.root.visibility = if(binding.itemMainRvContentsLayoutBook.root.visibility == View.VISIBLE) View.GONE else View.VISIBLE
    }

    fun onMovieClicked() {
        binding.itemMainRvContentsLayoutMovie.root.visibility = if(binding.itemMainRvContentsLayoutMovie.root.visibility == View.VISIBLE) View.GONE else View.VISIBLE
    }
}