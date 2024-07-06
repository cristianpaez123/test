package com.example.test.data.network

import com.example.test.data.response.LoginResponse
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthenticationService @Inject constructor(private val firebase: FirebaseClient) {

    suspend fun login(email: String, password: String): LoginResponse = runCatching {
        firebase.auth.signInWithEmailAndPassword(email, password).await()
    }.toLoginResult()

    private fun Result<AuthResult>.toLoginResult() = when (val result = getOrNull()) {
        null -> LoginResponse.error
        else -> {
            val userId = result.user
            checkNotNull(userId)
            LoginResponse.success(result.user?.isEmailVerified ?: false)
        }
    }

    suspend fun getId(): String? = firebase.idUser
}