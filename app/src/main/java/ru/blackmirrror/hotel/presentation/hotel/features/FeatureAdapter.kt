package ru.blackmirrror.hotel.presentation.hotel.features

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.blackmirrror.hotel.R
import ru.blackmirrror.hotel.domain.models.local.Feature

class FeatureAdapter :
    ListAdapter<Feature, FeatureAdapter.FeatureViewHolder>(FeatureItemDiffCallback()) {

    class FeatureViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val icon = itemView.findViewById<ImageView>(R.id.img_icon)
        val title = itemView.findViewById<TextView>(R.id.tv_name)
        val description = itemView.findViewById<TextView>(R.id.tv_description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeatureViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_feature_button, parent, false)
        return FeatureViewHolder(view)
    }

    override fun onBindViewHolder(holder: FeatureViewHolder, position: Int) {
        val feature = getItem(position)
        holder.icon.setImageResource(feature.imageRes)
        holder.title.text = feature.title
        holder.description.text = feature.description
    }
}