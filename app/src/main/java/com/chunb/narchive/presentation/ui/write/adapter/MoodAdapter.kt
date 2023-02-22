package com.chunb.narchive.presentation.ui.write.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chunb.narchive.data.local.data.MoodData
import com.chunb.narchive.databinding.ItemMoodsForEachBinding
import com.chunb.narchive.presentation.ui.main.feed.adapter.HomeFeedAdapter

class MoodAdapter() : RecyclerView.Adapter<MoodAdapter.MoodViewHolder>() {
    var moods = mutableListOf<MoodData>()

    private lateinit var moodClickedListener : MoodClickedListener

    interface MoodClickedListener {
        fun moodClickedListener (view : View, position : Int)
    }
    fun moodClickedListener(clickListener : MoodClickedListener) {
        this.moodClickedListener = clickListener
    }
    inner class MoodViewHolder(private val binding: ItemMoodsForEachBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : MoodData) {
            binding.moods = item.moods.second
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoodViewHolder {
        return MoodViewHolder(ItemMoodsForEachBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = moods.size

    override fun onBindViewHolder(holder: MoodViewHolder, position: Int) {
        holder.bind(moods[position])

        holder.itemView.setOnClickListener {
            moodClickedListener.moodClickedListener(it, position)
        }
    }
}