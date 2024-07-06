package com.example.test.domain

import com.example.test.data.network.AuthenticationService
import com.example.test.data.response.LoginResponse
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val authenticationService: AuthenticationService) {
    suspend operator fun invoke(email: String, password: String): LoginResponse =
        authenticationService.login(email, password)
}