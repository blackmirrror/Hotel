package ru.blackmirrror.hotel.presentation.hotel

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import ru.blackmirrror.hotel.R
import ru.blackmirrror.hotel.databinding.FragmentHotelBinding


class HotelFragment : Fragment() {

    private lateinit var binding: FragmentHotelBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHotelBinding.inflate(inflater, container, false)

        setUpNavigation()

        return binding.root
    }

    private fun setUpNavigation() {
        binding.btnHotelToRoom.btnNext.setOnClickListener {
            val action = HotelFragmentDirections.actionHotelFragmentToRoomFragment()
            Navigation.findNavController(binding.root).navigate(action)
        }
    }
}