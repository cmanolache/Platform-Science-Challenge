package com.constantin.platformscienceapp.ui.drivers

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.constantin.platformscienceapp.domain.models.Driver
import com.constantin.platformscienceapp.domain.usecase.GetDriverUseCase
import kotlinx.coroutines.launch

class DriverViewModel(private val driverUseCase: GetDriverUseCase) : ViewModel() {
    private val _uiState = MutableLiveData<DriverUiState>()
    val uiState: LiveData<DriverUiState>
        get() = _uiState

    fun getDrivers() {
        _uiState.postValue(DriverUiState.Loading)

        viewModelScope.launch {
            try {
                val drivers: List<Driver>? = driverUseCase()
                _uiState.postValue(DriverUiState.Success(drivers))
            } catch (e: Exception) {
                e.printStackTrace()
                _uiState.postValue(DriverUiState.Error("Error: Failed to load drivers!"))
            }
        }
    }
}

sealed class DriverUiState {
    object Loading : DriverUiState()
    class Success(val drivers: List<Driver>?) : DriverUiState()
    class Error(val message: String) : DriverUiState()
}
