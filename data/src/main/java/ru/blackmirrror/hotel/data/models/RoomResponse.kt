package ru.blackmirrror.hotel.data.models

import com.google.gson.annotations.SerializedName
import ru.blackmirrror.hotel.domain.models.Room

data class RoomResponse (
    @SerializedName("id"            ) var id            : Int?              = null,
    @SerializedName("name"          ) var name          : String?           = null,
    @SerializedName("price"         ) var price         : Int?              = null,
    @SerializedName("price_per"     ) var pricePer      : String?           = null,
    @SerializedName("peculiarities" ) var peculiarities : ArrayList<String> = arrayListOf(),
    @SerializedName("image_urls"    ) var imageUrls     : ArrayList<String> = arrayListOf()
) {
    companion object {
        fun dataToDomain(roomResponse: RoomResponse): Room {
            return Room(
                id = roomResponse.id,
                name = roomResponse.name,
                price = roomResponse.price,
                pricePer = roomResponse.pricePer,
                peculiarities = roomResponse.peculiarities,
                imageUrls = roomResponse.imageUrls
            )
        }
    }
}