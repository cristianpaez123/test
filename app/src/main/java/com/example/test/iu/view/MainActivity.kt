package com.example.test.iu.view

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import com.example.test.R
import com.example.test.databinding.ActivityMainBinding
import com.example.test.iu.viewModel.MainViemModel
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import com.google.firebase.Firebase
import com.google.firebase.auth.auth


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth


    private val viemModel: MainViemModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        auth = Firebase.auth
        setupObserver()
        binding.btnLogIn.setOnClickListener {
            logIn(binding.editTextEmail.text.toString(), binding.editTextPassword.text.toString())
        }
    }

    private fun setupObserver() {
        viemModel.dataModel.observe(this, Observer {
            binding.textData.text = it.text
        })
    }

    private fun logIn(email: String, password: String) {
        if (email.isNotEmpty() && password.isNotEmpty()) {
            FirebaseAuth.getInstance()
                .signInWithEmailAndPassword(email, password).addOnCompleteListener {
                    if (it.isSuccessful) {
                        binding.textData.text = getIdUser()
                    } else {
                        Toast.makeText(this@MainActivity, "pailas", Toast.LENGTH_LONG).show()
                    }
                }
        } else {
            Toast.makeText(this@MainActivity, "perrito ingrese datos", Toast.LENGTH_LONG).show()
        }
    }

    private fun getIdUser(): String {
        val user = auth.currentUser
        return user?.uid ?: ""
    }
}