package com.constantin.platformscienceapp.ui.shipment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.constantin.platformscienceapp.domain.models.Driver
import com.constantin.platformscienceapp.domain.models.Shipment
import com.constantin.platformscienceapp.domain.usecase.GetShipmentUseCase
import com.constantin.platformscienceapp.domain.usecase.MappingUseCase
import kotlinx.coroutines.launch

class ShipmentViewModel(
    private val shipmentUseCase: GetShipmentUseCase,
    private val mappingUseCase: MappingUseCase
) : ViewModel() {

    private val _shipmentState = MutableLiveData<ShipmentState>()
    val shipmentState: LiveData<ShipmentState>
        get() = _shipmentState

    fun getShipmentForDriver(driver: Driver) {
        _shipmentState.value = ShipmentState.Loading

        viewModelScope.launch {
            try {
                mappingUseCase()
                val shipment = shipmentUseCase(driver)
                if (shipment != null) {
                    _shipmentState.value = ShipmentState.Success(shipment)
                } else {
                    _shipmentState.value = ShipmentState.Error("Error: Shipment not found")
                }

            } catch (e: Exception) {
                _shipmentState.value = ShipmentState.Error(e.message ?: "Unknown error")
            }
        }
    }
}

sealed class ShipmentState {
    object Loading : ShipmentState()
    data class Success(val shipment: Shipment) : ShipmentState()
    data class Error(val message: String) : ShipmentState()
}
