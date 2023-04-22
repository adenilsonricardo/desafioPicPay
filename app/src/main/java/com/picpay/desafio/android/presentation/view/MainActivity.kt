package com.picpay.desafio.android.presentation.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.picpay.desafio.android.R
import com.picpay.desafio.android.data.models.User
import com.picpay.desafio.android.databinding.ActivityMainBinding
import com.picpay.desafio.android.presentation.viewModel.UsersViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: UsersViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.userListProgressBar.visibility = View.VISIBLE
        viewModel.getUsers()
        getObserver()


    }

    private fun getObserver() {
        viewModel.users.observe(this@MainActivity, Observer {
            setView(it)
        })
        viewModel.error.observe(this@MainActivity, Observer {
            setError()
        })
    }

    private fun setError() {
        val message = getString(R.string.error)

        binding.userListProgressBar.visibility = View.GONE
        binding.recyclerView.visibility = View.GONE

        Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT)
            .show()
    }

    private fun setView(users: List<User>) {
        binding.userListProgressBar.visibility = View.GONE
        val adapter = UsersAdapter(users)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)

    }

}
