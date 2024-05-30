package com.example.test.data.model

import javax.inject.Inject

class DataProvider @Inject constructor(){
    val data = listOf<DataModel>(
        DataModel("importar texto")
    )
}