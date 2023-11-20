package ru.blackmirrror.hotel.presentation.booking.tourists

import androidx.recyclerview.widget.DiffUtil.ItemCallback
import ru.blackmirrror.hotel.domain.models.local.Tourist

class TouristItemDiffCallback: ItemCallback<Tourist>() {
    override fun areItemsTheSame(oldItem: Tourist, newItem: Tourist): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Tourist, newItem: Tourist): Boolean {
        return oldItem == newItem
    }
}