package ru.blackmirrror.hotel.presentation.payment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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
        setOrderNumber()

        return binding.root
    }

    private fun setUpNavigation() {
        binding.toolbar.title.text = getString(R.string.order_paid)
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

    private fun setOrderNumber() {
        val orderNumber = arguments?.getInt(getString(R.string.argument_order_number)) ?: -1
        val message =
            "${getString(R.string.msg_order_first)}${orderNumber} ${getString(R.string.msg_order_last)}"
        binding.message.text = message
    }
}