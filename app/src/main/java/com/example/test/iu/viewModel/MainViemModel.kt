package com.example.test.iu.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test.data.model.DataModel
import com.example.test.data.model.DataProvider
import com.example.test.domain.GetDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViemModel @Inject constructor(
    private val getDataUseCase: GetDataUseCase
) : ViewModel() {

    val dataModel = MutableLiveData<DataModel>()

    init {
        viewModelScope.launch {
            val result = getDataUseCase.invoke()
            dataModel.postValue(result[0])
        }
    }

}