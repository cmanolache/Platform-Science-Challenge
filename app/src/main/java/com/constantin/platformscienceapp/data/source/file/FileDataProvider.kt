package com.constantin.platformscienceapp.data.source.file

import android.app.Application
import com.constantin.platformscienceapp.data.source.file.models.FileData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FileDataProvider(application: Application) {
    private val appContext = application.applicationContext
    private var response: FileData? = null

    private fun readFile(fileName: String = "data.json"): String =
        appContext.assets.open(fileName).bufferedReader().use {
            it.readText()
        }

    private suspend fun getFileData(): FileData? {
        if (response == null) {
            val jsonString = withContext(Dispatchers.IO) { readFile() }
            val responseType = object : TypeToken<FileData>() {}.type
            response = Gson().fromJson(jsonString, responseType)
        }
        return response
    }

    suspend fun getShipments(): List<String>? = getFileData()?.shipments

    suspend fun getDrivers(): List<String>? = getFileData()?.drivers
}




