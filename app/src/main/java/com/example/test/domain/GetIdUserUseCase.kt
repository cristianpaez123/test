package com.example.test.domain

import com.example.test.data.network.AuthenticationService
import javax.inject.Inject

class GetIdUserUseCase @Inject constructor(private val authenticationService: AuthenticationService) {
    suspend operator fun invoke(): String? =
        authenticationService.getId()
}