package com.chunb.narchive.presentation.ui.detail.view

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
import com.chunb.narchive.presentation.ui.detail.adapter.DetailImageAdapter
import com.chunb.narchive.presentation.ui.detail.viewmodel.DetailViewModel
import com.chunb.narchive.presentation.util.changeYFromTouch
import com.chunb.narchive.presentation.util.getCloseDrawerAnim
import com.chunb.narchive.presentation.util.getOpenAnim
import com.chunb.narchive.presentation.util.getOpenDrawerAnim
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private val viewModel: DetailViewModel by viewModels()

    var oldY = 0F
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)

        initBinding()
        getContentDetailData()
        observeDetail()
    }

    private fun initBinding() {
        binding.activity = this
    }

    private fun getContentDetailData() {
        val contentId = intent.getIntExtra("contentIdx", 0)
        viewModel.getDetailContent(contentId)
    }

    private fun observeDetail() {
        viewModel.contentData.observe(this) { it ->
            Log.d("----", "observeDetail: ${it.book}")
            binding.content = it
            binding.mood = Mood.valueOf(it.mood).res
            it.images?.let {images -> setVp(images) }
            if(it.book?.isNotEmpty() == true) setBook(it.book[0])
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

    private fun setBook(book : Book) {
        binding.itemMainRvContentsLayoutBook.apply {
                    setOnInflateListener { _, _ ->
                        val bookBinding = this.binding as ItemFormBookBinding
                        bookBinding.bookData = book
                    }
                }
                binding.itemMainRvContentsLayoutBook.viewStub?.inflate()
    }

    private fun setMovie(movie : Movie) {
        binding.itemMainRvContentsLayoutMovie.apply {
                    setOnInflateListener { _, _ ->
                        val movieBinding = this.binding as ItemFormMovieBinding
                        movieBinding.movieData = movie
                    }
                }
                binding.itemMainRvContentsLayoutMovie.viewStub?.inflate()
    }

    fun initBookDrawerTouched(view : View, event : MotionEvent) : Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                oldY = event.y
            }
            MotionEvent.ACTION_MOVE -> {
                val movedY: Float = event.y - oldY
                view.y = view.y + movedY
                binding.itemMainRvContentsLayoutIncludeBook.y = view.y + movedY + view.height
            }
            MotionEvent.ACTION_UP -> {
                getOpenDrawerAnim(binding.itemMainRvContentsLayoutIncludeBook).start()
            }
        }
        return true
    }

    fun initCloseDrawer(view : View) {
        getCloseDrawerAnim(view).start()
    }
}