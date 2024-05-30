package com.example.test.domain

import com.example.test.data.DataRepository
import com.example.test.data.model.DataModel
import javax.inject.Inject

class GetDataUseCase @Inject constructor(private val dataRepository: DataRepository){
    suspend operator fun invoke():List<DataModel> = dataRepository.getData()
}