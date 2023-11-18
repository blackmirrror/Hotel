package ru.blackmirrror.hotel.presentation.room

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.blackmirrror.hotel.databinding.FragmentRoomBinding
import ru.blackmirrror.hotel.presentation.hotel.peculiarity.PeculiarityAdapter


class RoomFragment : Fragment() {

    private lateinit var binding: FragmentRoomBinding
    private val viewModel by viewModel<RoomViewModel>()

    private lateinit var roomAdapter: RoomAdapter
    private lateinit var peculiarityAdapter: PeculiarityAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRoomBinding.inflate(inflater, container, false)

        setUpNavigation()
        setUpRoomRecycler()
        observeRooms()

        return binding.root
    }

    private fun setUpNavigation() {
        binding.toolbar.btnBack.setOnClickListener {
            Navigation.findNavController(binding.root).popBackStack()
        }
    }

    private fun setUpRoomRecycler() {
        roomAdapter = RoomAdapter()
        roomAdapter.onRoomNextBtnClickListener = {
            val action = RoomFragmentDirections.actionRoomFragmentToBookingFragment()
            Navigation.findNavController(binding.root).navigate(action)
        }
        binding.listRooms.adapter = roomAdapter
    }

    private fun observeRooms() {
        viewModel.rooms.observe(viewLifecycleOwner) {rooms ->
            roomAdapter.submitList(rooms)
        }
    }
}