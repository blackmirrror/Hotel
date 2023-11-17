package ru.blackmirrror.hotel.data.models

import com.google.gson.annotations.SerializedName
import ru.blackmirrror.hotel.domain.models.Hotel
import kotlin.math.min


data class HotelResponse (
    @SerializedName("id"              ) var id            : Int?              = null,
    @SerializedName("name"            ) var name          : String?           = null,
    @SerializedName("adress"          ) var adress        : String?           = null,
    @SerializedName("minimal_price"   ) var minimalPrice  : Int?              = null,
    @SerializedName("price_for_it"    ) var priceForIt    : String?           = null,
    @SerializedName("rating"          ) var rating        : Int?              = null,
    @SerializedName("rating_name"     ) var ratingName    : String?           = null,
    @SerializedName("image_urls"      ) var imageUrls     : ArrayList<String> = arrayListOf(),
    @SerializedName("about_the_hotel" ) var aboutTheHotel : AboutTheHotelResponse?    = AboutTheHotelResponse()
) {
    companion object {
        fun dataToDomain(hotelResponse: HotelResponse): Hotel {
            return Hotel(
                id = hotelResponse.id,
                name = hotelResponse.name,
                adress = hotelResponse.adress,
                minimalPrice = hotelResponse.minimalPrice,
                priceForIt = hotelResponse.priceForIt,
                rating = hotelResponse.rating,
                ratingName = hotelResponse.ratingName,
                imageUrls = hotelResponse.imageUrls,
                aboutTheHotel = hotelResponse.aboutTheHotel?.let {
                    AboutTheHotelResponse.dataToDomain(
                        it
                    )
                }
            )
        }
    }
}