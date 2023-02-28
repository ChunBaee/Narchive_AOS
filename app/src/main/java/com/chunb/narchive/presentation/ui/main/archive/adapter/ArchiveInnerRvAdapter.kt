package com.chunb.narchive.presentation.ui.main.archive.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chunb.narchive.data.remote.response.ArchiveBook
import com.chunb.narchive.data.remote.response.ArchiveMovie
import com.chunb.narchive.data.remote.response.Image
import com.chunb.narchive.databinding.ItemFormBookBinding
import com.chunb.narchive.databinding.ItemFormGridImageBinding
import com.chunb.narchive.databinding.ItemFormMovieBinding
import com.chunb.narchive.domain.mapper.mapToBook
import com.chunb.narchive.domain.mapper.mapToMovie

class ArchiveInnerRvAdapter(private val viewType: Int, private val onClick : (Int) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var mBookList = mutableListOf<ArchiveBook>()
    var mMovieList = mutableListOf<ArchiveMovie>()
    var mImageList = mutableListOf<Image>()

    inner class BookViewHolder(private val binding: ItemFormBookBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ArchiveBook) {
            binding.contentIdx = item.contentIdx
            binding.bookData = item.mapToBook()
            binding.root.setOnClickListener {
                onClick(item.contentIdx)
            }
        }
    }

    inner class MovieViewHolder(private val binding: ItemFormMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ArchiveMovie) {
            binding.contentIdx = item.contentIdx
            binding.movieData = item.mapToMovie()
            binding.root.setOnClickListener {
                onClick(item.contentIdx)
            }
        }
    }

    inner class ImageViewHolder(private val binding: ItemFormGridImageBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Image) {
            binding.contentIdx = item.contentIdx
            binding.imagePath = item.image
            binding.root.setOnClickListener {
                onClick(item.contentIdx)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            0 -> BookViewHolder(ItemFormBookBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            1 -> MovieViewHolder(ItemFormMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            else -> ImageViewHolder(ItemFormGridImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is BookViewHolder -> holder.bind(mBookList[position])

            is MovieViewHolder -> holder.bind(mMovieList[position])

            is ImageViewHolder -> holder.bind(mImageList[position])
        }
    }

    override fun getItemCount(): Int {
        return when (viewType) {
            0 -> mBookList.size
            1 -> mMovieList.size
            2 -> mImageList.size
            else -> mImageList.size
        }
    }

    override fun getItemViewType(position: Int): Int = viewType

    fun setBook(bookL: MutableList<ArchiveBook>) {
        this.mBookList = bookL
        notifyDataSetChanged()
    }

    fun setMovie(movieL: MutableList<ArchiveMovie>) {
        this.mMovieList = movieL
        notifyDataSetChanged()
    }

    fun setImage(imageL: MutableList<Image>) {
        this.mImageList = imageL
        notifyDataSetChanged()
    }
}