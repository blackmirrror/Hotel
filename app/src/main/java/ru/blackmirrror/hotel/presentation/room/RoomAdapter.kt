package ru.blackmirrror.hotel.presentation.room

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.button.MaterialButton
import ru.blackmirrror.hotel.R
import ru.blackmirrror.hotel.domain.models.Room
import ru.blackmirrror.hotel.presentation.hotel.images.ImageAdapter
import ru.blackmirrror.hotel.presentation.hotel.peculiarity.PeculiarityAdapter
import ru.blackmirrror.hotel.presentation.utils.TextFormatter

class RoomAdapter : ListAdapter<Room, RoomAdapter.RoomViewHolder>(RoomItemDiffCallback()) {

    lateinit var onRoomClickListener: OnRoomClickListener

    class RoomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val title = itemView.findViewById<TextView>(R.id.room_title)
        val price = itemView.findViewById<TextView>(R.id.price)
        val pricePer = itemView.findViewById<TextView>(R.id.price_per)
        val btnNext = itemView.findViewById<MaterialButton>(R.id.btn_room_to_booking)
        val peculiarities = itemView.findViewById<RecyclerView>(R.id.list_peculiarities)
        val images = itemView.findViewById<RecyclerView>(R.id.photo_carousel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_room, parent, false)
        return RoomViewHolder(view)
    }

    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {
        val room = getItem(position)
        holder.title.text = room.name
        holder.price.text = room.price?.let { TextFormatter.formatPrice(it) }
        holder.pricePer.text = room.pricePer?.lowercase()
        holder.btnNext.setOnClickListener {
            room.id?.let { it1 -> onRoomClickListener.onRoomClick(it1) }
        }

        setPeculiarities(holder, room)
        setImages(holder, room)
    }

    private fun setPeculiarities(
        holder: RoomViewHolder,
        room: Room
    ) {
        holder.peculiarities.layoutManager = StaggeredGridLayoutManager(
            2,
            StaggeredGridLayoutManager.HORIZONTAL
        )
        val adapter = PeculiarityAdapter()
        adapter.submitList(room.peculiarities)
        holder.peculiarities.adapter = adapter
    }

    private fun setImages(
        holder: RoomViewHolder,
        room: Room
    ) {
        val adapter = ImageAdapter()
        adapter.submitList(room.imageUrls)
        holder.images.adapter = adapter
    }

    interface OnRoomClickListener {
        fun onRoomClick(roomId: Int)
    }
}