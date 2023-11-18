package ru.blackmirrror.hotel.presentation.hotel.peculiarity

import androidx.recyclerview.widget.DiffUtil.ItemCallback

class PeculiarityItemDiffCallback: ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

}