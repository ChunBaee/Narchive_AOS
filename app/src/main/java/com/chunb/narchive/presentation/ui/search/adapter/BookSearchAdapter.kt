package com.chunb.narchive.presentation.ui.search.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.chunb.narchive.data.remote.response.Book
import com.chunb.narchive.data.remote.response.ResultSearchBook
import com.chunb.narchive.databinding.ItemFormBookBinding
import com.chunb.narchive.domain.mapper.mapToBook

class BookSearchAdapter : PagingDataAdapter<ResultSearchBook, BookSearchAdapter.BookSearchViewHolder>(diffCallback) {

    private lateinit var bookClickedListener: BookClickedListener

    interface BookClickedListener {
        fun bookClickedListener(view: View, item : Book)
    }

    fun bookClickedListener(clickListener: BookClickedListener) {
        this.bookClickedListener = clickListener
    }
    inner class BookSearchViewHolder(private val binding : ItemFormBookBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : ResultSearchBook) {
            binding.bookData = item.mapToBook()
        }
    }

    override fun onBindViewHolder(holder: BookSearchViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }

        holder.itemView.setOnClickListener {
            bookClickedListener.bookClickedListener(it, getItem(position)!!.mapToBook())
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookSearchViewHolder {
        return BookSearchViewHolder(ItemFormBookBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<ResultSearchBook>() {

            override fun areItemsTheSame(oldItem: ResultSearchBook, newItem: ResultSearchBook): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(oldItem: ResultSearchBook, newItem: ResultSearchBook): Boolean {
                return oldItem == newItem
            }
        }
    }


}