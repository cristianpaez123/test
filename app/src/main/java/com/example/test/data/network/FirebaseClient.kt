package com.example.test.data.network

import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import javax.inject.Inject

class FirebaseClient @Inject constructor() {

    val auth: FirebaseAuth get() = FirebaseAuth.getInstance()
    val idUser : String? get() = Firebase.auth.currentUser?.uid
}