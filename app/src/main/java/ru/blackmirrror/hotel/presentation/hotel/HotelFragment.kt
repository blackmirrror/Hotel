package ru.blackmirrror.hotel.presentation.hotel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.blackmirrror.hotel.R
import ru.blackmirrror.hotel.databinding.FragmentHotelBinding
import ru.blackmirrror.hotel.presentation.hotel.features.FeatureAdapter
import ru.blackmirrror.hotel.presentation.hotel.images.ImageAdapter
import ru.blackmirrror.hotel.presentation.hotel.peculiarity.PeculiarityAdapter
import ru.blackmirrror.hotel.presentation.utils.TextFormatter


class HotelFragment : Fragment() {

    private lateinit var binding: FragmentHotelBinding
    private val viewModel by viewModel<HotelViewModel>()

    private lateinit var peculiarityAdapter: PeculiarityAdapter
    private lateinit var imageAdapter: ImageAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHotelBinding.inflate(inflater, container, false)

        setUpFeatures()
        setUpPeculiarities()
        setUpImages()
        setUpFields()

        return binding.root
    }

    private fun setUpNavigation(hotelId: Int) {
        binding.btnHotelToRoom.btnNext.setOnClickListener {
            val action = HotelFragmentDirections.actionHotelFragmentToRoomFragment(hotelId)
            Navigation.findNavController(binding.root).navigate(action)
        }
    }

    private fun setUpFields() {
        viewModel.hotel.observe(viewLifecycleOwner) { it1 ->
            val hotel = it1 ?: return@observe
            binding.info.name.text = hotel.name
            binding.info.address.text = hotel.adress
            binding.info.ratingLayout.rating.text = "${hotel.rating} ${hotel.ratingName}"

            binding.price.text = hotel.minimalPrice?.let { "от ${TextFormatter.formatPrice(it)}" }
            binding.pricePer.text = hotel.priceForIt?.lowercase()

            binding.tvDescription.text = hotel.aboutTheHotel?.description
            binding.btnHotelToRoom.btnNext.text = getString(R.string.action_choose_rooms)
            peculiarityAdapter.submitList(hotel.aboutTheHotel?.peculiarities)
            imageAdapter.submitList(hotel.imageUrls)

            setUpNavigation(hotel.id ?: -1)
        }
    }

    private fun setUpFeatures() {
        val featureAdapter = FeatureAdapter()
        featureAdapter.submitList(viewModel.features)
        binding.listFeatures.adapter = featureAdapter
    }

    private fun setUpImages() {
        imageAdapter = ImageAdapter()
        binding.listPhoto.adapter = imageAdapter
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