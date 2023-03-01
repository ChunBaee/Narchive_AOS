package com.chunb.narchive.presentation.ui.main.setting.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chunb.narchive.data.data.SettingMenuData
import com.chunb.narchive.databinding.ItemSettingRvMenuBinding

class SettingMenuAdapter : RecyclerView.Adapter<SettingMenuAdapter.SettingMenuViewHolder>() {

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SettingMenuViewHolder {
        return SettingMenuViewHolder(ItemSettingRvMenuBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int = settingDatas.size

    override fun onBindViewHolder(holder: SettingMenuViewHolder, position: Int) {
        holder.bind(settingDatas[position])

        holder.itemView.setOnClickListener {
            menuClickedListener.menuClickedListener(it, position)
        }
    }
}