package ru.blackmirrror.hotel.presentation.booking

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.blackmirrror.hotel.R
import ru.blackmirrror.hotel.databinding.FragmentBookingBinding
import ru.blackmirrror.hotel.presentation.utils.FieldsChecker
import ru.blackmirrror.hotel.presentation.utils.TextFormatter


class BookingFragment : Fragment() {

    private lateinit var binding: FragmentBookingBinding
    private val viewModel by viewModel<BookingViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBookingBinding.inflate(inflater, container, false)

        setUpNavigation()
        fillFields()
        setUpCustomer()

        return binding.root
    }

    private fun setUpNavigation() {
        binding.toolbar.btnBack.setOnClickListener {
            Navigation.findNavController(binding.root).popBackStack()
        }
        binding.actionButtonLayout.btnNext.setOnClickListener {
            toPayment(binding.bookingCustomer.etMail.text.toString())
        }
    }

    private fun fillFields() {
        viewModel.booking.observe(viewLifecycleOwner) {booking ->
            with(binding.bookingData) {
                hotel.text = booking?.hotelName
                countryCity.text = booking?.arrivalCountry
                departureFrom.text = booking?.departure
                dates.text = "${booking?.tourDateStart} - ${booking?.tourDateStop}"
                nightsCount.text = "${booking?.numberOfNights} ночей"
                room.text = booking?.room
                nutrition.text = booking?.nutrition
            }

            with(binding.bookingPrice) {
                tour.text = booking?.tourPrice?.let { TextFormatter.formatPrice(it) }
                fuelCharge.text = booking?.fuelCharge?.let { TextFormatter.formatPrice(it) }
                serviceCharge.text = booking?.serviceCharge?.let { TextFormatter.formatPrice(it) }
                total.text = TextFormatter.formatPrice(booking?.tourPrice!!
                        + booking.fuelCharge!! + booking.serviceCharge!!
                )
            }
            binding.actionButtonLayout.btnNext.text = "Оплатить " +
                TextFormatter.formatPrice(booking?.tourPrice!!
                    + booking?.fuelCharge!! + booking?.serviceCharge!!
            )
        }
    }

    private fun setUpCustomer() {
        binding.bookingCustomer.etPhone.onFocusChangeListener =
            View.OnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    binding.bookingCustomer.etPhone.setText("7")
                }
        }
    }

    private fun toPayment(email: String) {
        if (FieldsChecker.checkEmail(email)) {
            val action = BookingFragmentDirections.actionBookingFragmentToPaymentFragment()
            Navigation.findNavController(binding.root).navigate(action)
        }
        else {
            binding.bookingCustomer.etMail.setBackgroundResource(R.drawable.bg_rounded_edittext_error)
        }
    }
}