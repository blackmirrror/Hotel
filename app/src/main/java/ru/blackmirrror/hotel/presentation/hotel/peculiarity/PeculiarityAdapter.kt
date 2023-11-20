package ru.blackmirrror.hotel.presentation.hotel.peculiarity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.blackmirrror.hotel.R


class PeculiarityAdapter :
    ListAdapter<String, PeculiarityAdapter.PeculiarityViewHolder>(PeculiarityItemDiffCallback()) {

    class PeculiarityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val text = itemView.findViewById<TextView>(R.id.tv_peculiarity)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeculiarityViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_peculiarity, parent, false)
        return PeculiarityViewHolder(view)
    }

    override fun onBindViewHolder(holder: PeculiarityViewHolder, position: Int) {
        val peculiarity = getItem(position)
        holder.text.text = peculiarity
    }
}