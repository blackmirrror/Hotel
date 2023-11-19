package ru.blackmirrror.hotel.presentation.payment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import ru.blackmirrror.hotel.R
import ru.blackmirrror.hotel.databinding.FragmentPaymentBinding


class PaymentFragment : Fragment() {

    private lateinit var binding: FragmentPaymentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPaymentBinding.inflate(inflater, container, false)

        setUpNavigation()

        return binding.root
    }

    private fun setUpNavigation() {
        binding.toolbar.btnBack.setOnClickListener {
            Navigation.findNavController(binding.root).popBackStack()
        }

        with(binding.actionButton.btnNext) {
            text = "Супер!"
            setOnClickListener {
                val action = PaymentFragmentDirections.actionPaymentFragmentToHotelFragment()
                Navigation.findNavController(binding.root).navigate(action)
            }
        }
    }
}