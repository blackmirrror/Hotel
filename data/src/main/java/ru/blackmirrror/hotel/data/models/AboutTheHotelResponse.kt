package ru.blackmirrror.hotel.data.models

import com.google.gson.annotations.SerializedName
import ru.blackmirrror.hotel.domain.models.AboutTheHotel


data class AboutTheHotelResponse (
    @SerializedName("description"   ) var description   : String?           = null,
    @SerializedName("peculiarities" ) var peculiarities : ArrayList<String> = arrayListOf()
) {
    companion object {
        fun dataToDomain(aboutTheHotelResponse: AboutTheHotelResponse): AboutTheHotel {
            return AboutTheHotel(
                description = aboutTheHotelResponse.description,
                peculiarities = aboutTheHotelResponse.peculiarities
            )
        }
    }
}