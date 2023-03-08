package com.constantin.platformscienceapp.ui.shipment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.constantin.platformscienceapp.R
import com.constantin.platformscienceapp.databinding.FragmentShipmentBinding
import com.constantin.platformscienceapp.domain.models.Driver
import org.koin.androidx.viewmodel.ext.android.viewModel

class ShipmentFragment : Fragment() {

    private lateinit var binding: FragmentShipmentBinding
    private val viewModel by viewModel<ShipmentViewModel>()

    private val driverName by lazy {
        arguments?.getString(DRIVER_NAME) ?: ""
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentShipmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.shipmentState.observe(viewLifecycleOwner) { shipment ->
            handleUiState(shipment)
        }

        // fetch shipment for driver
        viewModel.getShipmentForDriver(Driver(driverName))
    }

    private fun handleUiState(state: ShipmentState) {
        when (state) {
            is ShipmentState.Loading -> {
                binding.progressBar.visibility = View.VISIBLE
                binding.tvErrorMessage.visibility = View.GONE
                binding.textView.visibility = View.GONE
            }
            is ShipmentState.Success -> {
                binding.progressBar.visibility = View.GONE
                binding.tvErrorMessage.visibility = View.GONE
                binding.textView.visibility = View.VISIBLE
                binding.textView.text =
                    getString(R.string.shipment_text, state.shipment.destination)

            }
            is ShipmentState.Error -> {
                binding.progressBar.visibility = View.GONE
                binding.tvErrorMessage.visibility = View.VISIBLE
                binding.textView.visibility = View.GONE
                binding.tvErrorMessage.text = state.message
            }
        }
    }

    companion object {
        const val DRIVER_NAME = "driver_name"
    }
}
