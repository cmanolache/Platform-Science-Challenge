package com.constantin.platformscienceapp.data

import com.constantin.platformscienceapp.domain.models.Driver
import com.constantin.platformscienceapp.domain.models.Shipment

fun String.toDriver(): Driver {
    return Driver(name = this)
}

fun String.toShipment(): Shipment {
    return Shipment(destination = this)
}
