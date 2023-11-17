package ru.blackmirrror.hotel.presentation.hotel

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import ru.blackmirrror.hotel.R
import ru.blackmirrror.hotel.data.api.ApiFactory
import ru.blackmirrror.hotel.data.api.RemoteDataSourceImpl
import ru.blackmirrror.hotel.data.repositories.HotelRepositoryImpl
import ru.blackmirrror.hotel.databinding.FragmentHotelBinding
import ru.blackmirrror.hotel.domain.usecases.GetHotelUseCase


class HotelFragment : Fragment() {

    private lateinit var binding: FragmentHotelBinding
    private lateinit var viewModel: HotelViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHotelBinding.inflate(inflater, container, false)

        setUpNavigation()
        tempFun()

        return binding.root
    }

    private fun setUpNavigation() {
        binding.btnHotelToRoom.btnNext.setOnClickListener {
            val action = HotelFragmentDirections.actionHotelFragmentToRoomFragment()
            Navigation.findNavController(binding.root).navigate(action)
        }
    }

    private fun tempFun() {
        val apiService = ApiFactory.create()
        val remoteDataSource = RemoteDataSourceImpl(apiService)
        val repository = HotelRepositoryImpl(remoteDataSource)
        val getHotelUseCase = GetHotelUseCase(repository)
        viewModel = HotelViewModel(getHotelUseCase)

        viewModel.hotel.observe(viewLifecycleOwner) { hotel ->
            binding.info.name.text = hotel?.name
            binding.info.address.text = hotel?.adress
            binding.info.ratingLayout.rating.text = hotel?.rating.toString() + " " + hotel?.ratingName

            binding.price.text = hotel?.minimalPrice.toString()
            binding.pricePer.text = hotel?.priceForIt

            binding.tvDescription.text = hotel?.aboutTheHotel?.description

            binding.btnHotelToRoom.btnNext.text = "К выбору номера"
        }
    }
}