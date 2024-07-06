package com.example.test.iu.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test.data.model.DataModel
import com.example.test.data.model.DataProvider
import com.example.test.data.response.LoginResponse
import com.example.test.domain.GetDataUseCase
import com.example.test.domain.GetIdUserUseCase
import com.example.test.domain.LoginUseCase
import com.example.test.iu.view.LoginViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViemModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val getIdUserUseCase: GetIdUserUseCase
) : ViewModel() {

    private val _viewState = MutableLiveData(LoginViewState())
    val viewState: LiveData<LoginViewState>
        get() = _viewState

    private var _showErrorDialog = MutableLiveData<Boolean>()
    val showErrorDialog: LiveData<Boolean>
        get() = _showErrorDialog

    private var _showIdUser = MutableLiveData<String?>()
    val showIdUser: LiveData<String?>
        get() = _showIdUser

    fun loginUser(email:String, password:String){
        viewModelScope.launch {
            _viewState.value = LoginViewState(isLoading = true)
            when (loginUseCase(email,password)){
                LoginResponse.error -> {
                    _viewState.value = LoginViewState(isLoading = false)
                    _showErrorDialog.value = true}
                is LoginResponse.success -> {
                    _viewState.value = LoginViewState(isLoading = false)
                    _showIdUser.value = getIdUserUseCase.invoke()
                }
            }
        }
    }

}