package ru.blackmirrror.hotel.presentation.room

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import ru.blackmirrror.hotel.R
import ru.blackmirrror.hotel.databinding.FragmentRoomBinding


class RoomFragment : Fragment() {

    private lateinit var binding: FragmentRoomBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRoomBinding.inflate(inflater, container, false)

        setUpChangeFragmentButtons()

        return binding.root
    }

    private fun setUpChangeFragmentButtons() {
        binding.toolbar.btnBack.setOnClickListener {
            Navigation.findNavController(binding.root).popBackStack()
        }
    }
}