package ru.blackmirrror.hotel.data.models

import com.google.gson.annotations.SerializedName
import ru.blackmirrror.hotel.domain.models.Room

data class RoomsResponse (
    @SerializedName("rooms" ) var rooms : ArrayList<RoomResponse> = arrayListOf()
) {
    companion object {
        fun dataToDomain(roomsResponse: RoomsResponse): List<Room> {
            return roomsResponse.rooms.map { RoomResponse.dataToDomain(it) }
        }
    }
}