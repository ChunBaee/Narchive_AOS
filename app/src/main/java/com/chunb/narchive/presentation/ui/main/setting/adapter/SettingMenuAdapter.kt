package com.chunb.narchive.presentation.ui.main.setting.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.chunb.narchive.data.data.SettingMenuData
import com.chunb.narchive.databinding.ItemSettingMenuDividerBinding
import com.chunb.narchive.databinding.ItemSettingRvMenuBinding

class SettingMenuAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var menuClickedListener : MenuClickedListener
    var settingDatas = mutableListOf<SettingMenuData>()

    interface MenuClickedListener {
        fun menuClickedListener (view : View, position : Int)
    }
    fun menuClickedListener(clickListener : MenuClickedListener) {
        this.menuClickedListener = clickListener
    }


    inner class SettingMenuViewHolder(val binding : ItemSettingRvMenuBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : SettingMenuData) {
            binding.data = item
        }
    }

    inner class SettingMenuDividerViewHolder(val binding : ItemSettingMenuDividerBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(){}
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            0 -> SettingMenuDividerViewHolder(ItemSettingMenuDividerBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            else -> SettingMenuViewHolder(ItemSettingRvMenuBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }
    }

    override fun getItemViewType(position: Int): Int = settingDatas[position].image
    override fun getItemCount(): Int = settingDatas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when(holder) {
            is SettingMenuViewHolder -> {
                holder.bind(settingDatas[position])

                holder.itemView.setOnClickListener {
                    menuClickedListener.menuClickedListener(it, position)
                }
            }
            is SettingMenuDividerViewHolder -> holder.bind()
        }

    }
}