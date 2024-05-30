package com.example.test.data

import com.example.test.data.model.DataModel
import com.example.test.data.model.DataProvider
import javax.inject.Inject

class DataRepository @Inject constructor(val dataProvider: DataProvider) {
    suspend fun getData(): List<DataModel> {
        val response = dataProvider.data
        return response
    }
}