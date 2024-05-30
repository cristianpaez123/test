package com.example.test.domain

import com.example.test.data.DataRepository
import javax.inject.Inject

class GetDataUseCase @Inject constructor(private val dataRepository: DataRepository){
}