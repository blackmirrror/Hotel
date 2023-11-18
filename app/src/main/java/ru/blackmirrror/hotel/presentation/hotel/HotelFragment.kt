package ru.blackmirrror.hotel.presentation.hotel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.blackmirrror.hotel.databinding.FragmentHotelBinding
import ru.blackmirrror.hotel.presentation.hotel.features.FeatureAdapter
import ru.blackmirrror.hotel.presentation.hotel.peculiarity.PeculiarityAdapter


class HotelFragment : Fragment() {

    private lateinit var binding: FragmentHotelBinding
    private val viewModel by viewModel<HotelViewModel>()

    private lateinit var peculiarityAdapter: PeculiarityAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHotelBinding.inflate(inflater, container, false)

        setUpNavigation()
        setUpFeatures()
        setUpPeculiarities()
        setUpFields()

        return binding.root
    }

    private fun setUpNavigation() {
        binding.btnHotelToRoom.btnNext.setOnClickListener {
            val action = HotelFragmentDirections.actionHotelFragmentToRoomFragment()
            Navigation.findNavController(binding.root).navigate(action)
        }
    }

    private fun setUpFields() {
            viewModel.hotel.observe(viewLifecycleOwner) { hotel ->
            binding.info.name.text = hotel?.name
            binding.info.address.text = hotel?.adress
            binding.info.ratingLayout.rating.text = hotel?.rating.toString() + " " + hotel?.ratingName

            binding.price.text = hotel?.minimalPrice.toString()
            binding.pricePer.text = hotel?.priceForIt

            binding.tvDescription.text = hotel?.aboutTheHotel?.description

            binding.btnHotelToRoom.btnNext.text = "К выбору номера"

            peculiarityAdapter.submitList(hotel?.aboutTheHotel?.peculiarities)
        }
    }

    private fun setUpFeatures() {
        val featureAdapter = FeatureAdapter()
        featureAdapter.submitList(viewModel.features)
        binding.listFeatures.adapter = featureAdapter
    }

    private fun setUpPeculiarities() {
        binding.listPeculiarities.layoutManager = StaggeredGridLayoutManager(
            2,
            StaggeredGridLayoutManager.HORIZONTAL
        )

        peculiarityAdapter = PeculiarityAdapter()
        binding.listPeculiarities.adapter = peculiarityAdapter
    }
}