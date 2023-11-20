package ru.blackmirrror.hotel.presentation.hotel.images

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.blackmirrror.hotel.R

class ImageAdapter : ListAdapter<String, ImageAdapter.ImageViewHolder>(
    ImageItemDiffCallback()
) {

    class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val photo = itemView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_photo_carousel, parent, false)
        return ImageViewHolder(view)
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val imageUrl = getItem(position)
        GlobalScope.launch(Dispatchers.Main) {
            val bitmap = try {
                withContext(Dispatchers.IO) {
                    Glide.with(holder.itemView)
                        .asBitmap()
                        .load(imageUrl)
                        .apply(RequestOptions.centerCropTransform())
                        .submit()
                        .get()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            (holder.itemView as ImageView).setImageBitmap(bitmap as? Bitmap?)
        }
    }
}