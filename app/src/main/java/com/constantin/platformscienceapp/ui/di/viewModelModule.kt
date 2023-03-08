package com.constantin.platformscienceapp.ui.di

import com.constantin.platformscienceapp.ui.drivers.DriverViewModel
import com.constantin.platformscienceapp.ui.shipment.ShipmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        DriverViewModel(driverUseCase = get())
    }

    viewModel {
        ShipmentViewModel(
            shipmentUseCase = get(),
            mappingUseCase = get()
        )
    }
}
