package com.example.test.data.response

sealed class LoginResponse {
    object error : LoginResponse()
    data class success(val virified: Boolean) : LoginResponse()
}