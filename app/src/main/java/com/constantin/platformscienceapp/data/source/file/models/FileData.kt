package com.constantin.platformscienceapp.data.source.file.models

import com.google.gson.annotations.SerializedName

data class FileData(
    @SerializedName("shipments")
    val shipments: List<String>,

    @SerializedName("drivers")
    val drivers: List<String>,
)
