package com.constantin.platformscienceapp.ui.drivers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.constantin.platformscienceapp.R
import com.constantin.platformscienceapp.databinding.FragmentDriversBinding
import com.constantin.platformscienceapp.ui.shipment.ShipmentFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class DriversFragment : Fragment() {

    private lateinit var binding: FragmentDriversBinding
    private val driverViewModel by viewModel<DriverViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentDriversBinding.inflate(inflater, container, false)
        setupRecyclerView()
        driverViewModel.uiState.observe(viewLifecycleOwner) { handleUiState(it) }
        driverViewModel.getDrivers()
        return binding.root
    }

    private fun setupRecyclerView() {
        val driverAdapter = DriversAdapter { driver ->
            val bundle = bundleOf(ShipmentFragment.DRIVER_NAME to driver.name)
            findNavController().navigate(R.id.action_DriversFragment_to_ShipmentFragment, bundle)
        }

        binding.rvDrivers.apply {
            adapter = driverAdapter
            setHasFixedSize(true)
        }
    }

    private fun handleUiState(state: DriverUiState) {
        when (state) {
            is DriverUiState.Loading -> {
                binding.progressBar.visibility = View.VISIBLE
                binding.tvErrorMessage.visibility = View.GONE
                binding.rvDrivers.visibility = View.GONE
            }
            is DriverUiState.Success -> {
                binding.progressBar.visibility = View.GONE
                binding.tvErrorMessage.visibility = View.GONE
                binding.rvDrivers.visibility = View.VISIBLE
                (binding.rvDrivers.adapter as DriversAdapter).submitList(state.drivers)
            }
            is DriverUiState.Error -> {
                binding.progressBar.visibility = View.GONE
                binding.tvErrorMessage.visibility = View.VISIBLE
                binding.rvDrivers.visibility = View.GONE
                binding.tvErrorMessage.text = state.message
            }
        }
    }
}
