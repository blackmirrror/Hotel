package ru.blackmirrror.hotel.presentation.room

import androidx.recyclerview.widget.DiffUtil.ItemCallback
import ru.blackmirrror.hotel.domain.models.Room

class RoomItemDiffCallback: ItemCallback<Room>() {
    override fun areItemsTheSame(oldItem: Room, newItem: Room): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Room, newItem: Room): Boolean {
        return oldItem == newItem
    }
}