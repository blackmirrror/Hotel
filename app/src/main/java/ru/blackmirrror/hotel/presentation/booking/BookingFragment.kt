package ru.blackmirrror.hotel.presentation.booking

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.blackmirrror.hotel.R
import ru.blackmirrror.hotel.databinding.FragmentBookingBinding
import ru.blackmirrror.hotel.domain.models.Booking
import ru.blackmirrror.hotel.presentation.booking.tourists.TouristAdapter
import ru.blackmirrror.hotel.presentation.utils.FieldsChecker
import ru.blackmirrror.hotel.presentation.utils.TextFormatter


class BookingFragment : Fragment() {

    private lateinit var binding: FragmentBookingBinding
    private val viewModel by viewModel<BookingViewModel>()

    private lateinit var touristAdapter: TouristAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBookingBinding.inflate(inflater, container, false)

        loadData()
        setUpCustomer()
        setUpTourists()
        setUpNavigation()
        fillBookingFields()
        fillHotelFields()

        return binding.root
    }

    private fun loadData() {
        val roomId = arguments?.getInt(getString(R.string.argument_room_id)) ?: -1
        viewModel.getBooking(roomId)

        val hotelId = arguments?.getInt(getString(R.string.argument_hotel_id)) ?: -1
        viewModel.getHotel(hotelId)
    }

    private fun setUpNavigation() {
        binding.toolbar.title.text = getString(R.string.booking)
        binding.toolbar.btnBack.setOnClickListener {
            Navigation.findNavController(binding.root).popBackStack()
        }
        binding.actionButtonLayout.btnNext.setOnClickListener {
            toPayment(binding.bookingCustomer.etMail.text.toString())
        }
    }

    private fun fillBookingFields() {
        viewModel.booking.observe(viewLifecycleOwner) {
            val booking = it ?: return@observe
            setBookingData(booking)
            setBookingPrice(booking)

            binding.actionButtonLayout.btnNext.text = "Оплатить ${
                TextFormatter.formatPrice(
                    booking.tourPrice!!
                            + booking.fuelCharge!! + booking.serviceCharge!!
                )
            }"
        }
    }

    private fun setBookingData(booking: Booking) {
        with(binding.bookingData) {
            hotel.text = booking.hotelName
            countryCity.text = booking.arrivalCountry
            departureFrom.text = booking.departure
            dates.text = "${booking.tourDateStart} - ${booking.tourDateStop}"
            nightsCount.text = "${booking.numberOfNights} ночей"
            room.text = booking.room
            nutrition.text = booking.nutrition
        }
    }

    private fun setBookingPrice(booking: Booking) {
        with(binding.bookingPrice) {
            tour.text = booking.tourPrice?.let { TextFormatter.formatPrice(it) }
            fuelCharge.text = booking.fuelCharge?.let { TextFormatter.formatPrice(it) }
            serviceCharge.text = booking.serviceCharge?.let { TextFormatter.formatPrice(it) }
            total.text = TextFormatter.formatPrice(
                booking.tourPrice!!
                        + booking.fuelCharge!! + booking.serviceCharge!!
            )
        }
    }

    private fun fillHotelFields() {
        viewModel.hotel.observe(viewLifecycleOwner) { hotel ->
            with(binding.infoMain) {
                name.text = hotel?.name
                address.text = hotel?.adress
                ratingLayout.rating.text = "${hotel?.rating} ${hotel?.ratingName}"
            }
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

    private fun setUpTourists() {
        binding.newTourist.btnAction.setOnClickListener {
            viewModel.createTourist()
        }

        touristAdapter = TouristAdapter()
        viewModel.tourists.observe(viewLifecycleOwner) { tourists ->
            touristAdapter.submitList(tourists)
        }
        binding.listTourists.adapter = touristAdapter
    }

    private fun toPayment(email: String) {
        if (checkEmail(email) and checkTourists()) {
            val randomNumber = (COUNT_DIGITS_ORDER until COUNT_DIGITS_ORDER * 10).random()
            val action =
                BookingFragmentDirections.actionBookingFragmentToPaymentFragment(randomNumber)
            Navigation.findNavController(binding.root).navigate(action)
            viewModel.updateTourists(touristAdapter.getTourists(binding.listTourists))
        }

    }

    private fun checkEmail(email: String): Boolean {
        return if (FieldsChecker.checkEmail(email)) {
            true
        } else {
            binding.bookingCustomer.etMail.setBackgroundResource(R.drawable.bg_rounded_edittext_error)
            false
        }
    }

    private fun checkTourists(): Boolean {
        return touristAdapter.checkTourists(binding.listTourists)
    }

    companion object {
        private const val COUNT_DIGITS_ORDER = 100000
    }
}