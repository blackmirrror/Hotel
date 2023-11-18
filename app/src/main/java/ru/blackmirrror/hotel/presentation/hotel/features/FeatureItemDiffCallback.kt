package ru.blackmirrror.hotel.presentation.hotel.features

import androidx.recyclerview.widget.DiffUtil.ItemCallback
import ru.blackmirrror.hotel.domain.models.local.Feature

class FeatureItemDiffCallback: ItemCallback<Feature>() {
    override fun areItemsTheSame(oldItem: Feature, newItem: Feature): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Feature, newItem: Feature): Boolean {
        return oldItem == newItem
    }
}