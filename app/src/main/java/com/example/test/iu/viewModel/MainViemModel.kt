package com.example.test.iu.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.test.data.model.DataModel
import com.example.test.domain.GetDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViemModel @Inject constructor(
    private val getDataUseCase: GetDataUseCase
) : ViewModel() {

}