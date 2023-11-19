package ru.blackmirrror.hotel.presentation.room

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.blackmirrror.hotel.R
import ru.blackmirrror.hotel.databinding.FragmentRoomBinding
import ru.blackmirrror.hotel.presentation.hotel.peculiarity.PeculiarityAdapter


class RoomFragment : Fragment() {

    private lateinit var binding: FragmentRoomBinding
    private val viewModel by viewModel<RoomViewModel>()

    private lateinit var roomAdapter: RoomAdapter
    private lateinit var peculiarityAdapter: PeculiarityAdapter
    private var hotelId: Int = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRoomBinding.inflate(inflater, container, false)

        loadRoomsByHotelId()
        setUpNavigation()
        setUpRoomRecycler()
        observeData()

        return binding.root
    }

    private fun loadRoomsByHotelId() {
        hotelId = arguments?.getInt(getString(R.string.argument_hotel_id))?: -1
        viewModel.getRooms(hotelId)
        viewModel.getHotelName(hotelId)
    }

    private fun setUpNavigation() {
        binding.toolbar.btnBack.setOnClickListener {
            Navigation.findNavController(binding.root).popBackStack()
        }
    }

    private fun setUpRoomRecycler() {
        roomAdapter = RoomAdapter()
        roomAdapter.onRoomClickListener = object : RoomAdapter.OnRoomClickListener {
            override fun onRoomClick(roomId: Int) {
                val action = RoomFragmentDirections.actionRoomFragmentToBookingFragment(roomId, hotelId)
                Navigation.findNavController(binding.root).navigate(action)
            }
        }
        binding.listRooms.adapter = roomAdapter
    }

    private fun observeData() {
        viewModel.rooms.observe(viewLifecycleOwner) {rooms ->
            roomAdapter.submitList(rooms)
        }
        viewModel.hotelName.observe(viewLifecycleOwner) {hotelName ->
            binding.toolbar.title.text = hotelName
        }
    }
}