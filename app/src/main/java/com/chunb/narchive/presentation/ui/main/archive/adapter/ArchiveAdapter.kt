package com.chunb.narchive.presentation.ui.main.archive.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chunb.narchive.data.remote.response.ArchiveBook
import com.chunb.narchive.data.remote.response.ArchiveMovie
import com.chunb.narchive.data.remote.response.Image
import com.chunb.narchive.databinding.ItemArchiveVpContentsBinding

class ArchiveAdapter(private val context : Context) : RecyclerView.Adapter<ArchiveAdapter.ArchiveViewHolder>() {

    var selectedContentId = MutableLiveData<Int>()

    private val bookRvAdapter: ArchiveInnerRvAdapter by lazy {
        ArchiveInnerRvAdapter(getItemViewType(0), ::getContentIdx)
    }
    private val movieRvAdapter: ArchiveInnerRvAdapter by lazy {
        ArchiveInnerRvAdapter(getItemViewType(1), ::getContentIdx)
    }
    private val imageRvAdapter: ArchiveInnerRvAdapter by lazy {
        ArchiveInnerRvAdapter(getItemViewType(2), ::getContentIdx)
    }
    inner class ArchiveViewHolder(val binding: ItemArchiveVpContentsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            when(absoluteAdapterPosition) {
                0 -> {
                    binding.itemArchiveVpContentsInnerVp.layoutManager = LinearLayoutManager(context)
                    binding.itemArchiveVpContentsInnerVp.adapter = bookRvAdapter
                }
                1 -> {
                    binding.itemArchiveVpContentsInnerVp.layoutManager = LinearLayoutManager(context)
                    binding.itemArchiveVpContentsInnerVp.adapter = movieRvAdapter
                }
                2 -> {
                    binding.itemArchiveVpContentsInnerVp.layoutManager = GridLayoutManager(context,3)
                    binding.itemArchiveVpContentsInnerVp.adapter = imageRvAdapter
                }
            }
        }
    }

    private fun getContentIdx(selected : Int) {
        Log.d("----", "getContentIdx: $selected")
        selectedContentId.value = selected
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArchiveViewHolder {
        return ArchiveViewHolder(ItemArchiveVpContentsBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemViewType(position: Int): Int = position

    override fun onBindViewHolder(holder: ArchiveViewHolder, position: Int) {
        holder.bind()

    }
    override fun getItemCount(): Int = 3

    fun setBookList(bookList : List<ArchiveBook>) {
        bookRvAdapter.setBook(bookList as MutableList)
    }

    fun setMovieList(movieList : List<ArchiveMovie>) {
        movieRvAdapter.setMovie(movieList as MutableList)
    }

    fun setImageList(imageList : MutableList<Image>) {
        imageRvAdapter.setImage(imageList)
    }

}