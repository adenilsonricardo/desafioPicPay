package com.picpay.desafio.android.presentation.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.picpay.desafio.android.R
import com.picpay.desafio.android.data.models.User
import com.picpay.desafio.android.databinding.FragmentListUserBinding
import com.picpay.desafio.android.presentation.view.adapter.UserListAdapter
import com.picpay.desafio.android.presentation.viewModel.UserListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserListFragment : Fragment() {
    private lateinit var binding: FragmentListUserBinding
    private val viewModel: UserListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.userListProgressBar.visibility = View.VISIBLE

        viewModel.getUsers()
        getObserver()
    }

    private fun getObserver() {
        viewModel.users.observe(viewLifecycleOwner, Observer {
            setView(it)
        })
        viewModel.error.observe(viewLifecycleOwner, Observer {
            setError()
        })
    }

    private fun setView(users: List<User>) {
        binding.userListProgressBar.visibility = View.GONE
        val adapter = UserListAdapter(users)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
    }

    private fun setError() {
        val message = getString(R.string.error)

        binding.userListProgressBar.visibility = View.GONE
        binding.recyclerView.visibility = View.GONE

        Toast.makeText(context, message, Toast.LENGTH_SHORT)
            .show()
    }
}