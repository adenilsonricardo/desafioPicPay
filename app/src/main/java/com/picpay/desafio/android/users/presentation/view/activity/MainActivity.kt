package com.picpay.desafio.android.users.presentation.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.picpay.desafio.android.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}