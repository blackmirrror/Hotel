package ru.blackmirrror.hotel.presentation.booking.tourists

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import ru.blackmirrror.hotel.R
import ru.blackmirrror.hotel.domain.models.local.Tourist
import ru.blackmirrror.hotel.presentation.utils.TextFormatter

class TouristAdapter :
    ListAdapter<Tourist, TouristAdapter.TouristViewHolder>(TouristItemDiffCallback()) {

    class TouristViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val actionBtn = itemView.findViewById<ImageView>(R.id.btn_action)
        val title = itemView.findViewById<TextView>(R.id.title_tourist)
        val fieldsLayout = itemView.findViewById<LinearLayout>(R.id.ll_fields_tourist)

        val firstName = itemView.findViewById<TextInputEditText>(R.id.et_name)
        val lastName = itemView.findViewById<TextInputEditText>(R.id.et_second_name)
        val birthDate = itemView.findViewById<TextInputEditText>(R.id.et_birthday_date)
        val citizenship = itemView.findViewById<TextInputEditText>(R.id.et_citizenship)
        val passNum = itemView.findViewById<TextInputEditText>(R.id.et_passport_number)
        val passDate = itemView.findViewById<TextInputEditText>(R.id.et_passport_expiration_date)

        var isExpanded: Boolean = false
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TouristViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_tourist_expanded, parent, false)
        return TouristViewHolder(view)
    }

    override fun onBindViewHolder(holder: TouristViewHolder, position: Int) {
        val tourist = getItem(position)
        holder.actionBtn.setOnClickListener {
            changeExpanded(holder)
        }
        holder.title.text = "${TextFormatter.numberToWord(tourist.id)} турист"
    }

    private fun changeExpanded(holder: TouristViewHolder) {
        if (!holder.isExpanded) {
            holder.actionBtn.setImageResource(R.drawable.ic_arrow_down_square)
            holder.fieldsLayout.visibility = View.VISIBLE
        } else {
            holder.actionBtn.setImageResource(R.drawable.ic_arrow_up_square)
            holder.fieldsLayout.visibility = View.GONE
        }
        holder.isExpanded = !holder.isExpanded
    }

    fun checkTourists(recyclerView: RecyclerView): Boolean {
        for (i in 0 until itemCount) {
            val holder =
                recyclerView.findViewHolderForAdapterPosition(i) as TouristViewHolder?
                    ?: return true
            if (!holder.isExpanded) changeExpanded(holder)
            val res = (updateColor(holder.firstName) and updateColor(holder.lastName) and
                    updateColor(holder.birthDate) and updateColor(holder.citizenship)
                    and updateColor(holder.passNum) and updateColor(holder.passDate))
            if (!res) return false
        }
        return true
    }

    private fun updateColor(field: TextInputEditText): Boolean {
        val editTextValue = field.text.toString().trim()
        if (editTextValue.isEmpty()) {
            field.setBackgroundResource(R.drawable.bg_rounded_edittext_error)
            return false
        } else {
            field.setBackgroundResource(R.drawable.bg_rounded_edittext)
        }
        return true
    }
}