package com.picpay.desafio.android.users.presentation.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.databinding.FragmentListUserBinding
import com.picpay.desafio.android.users.domain.models.UsersDomain
import com.picpay.desafio.android.users.presentation.state.UserState
import com.picpay.desafio.android.users.presentation.view.adapter.UserListAdapter
import com.picpay.desafio.android.users.presentation.viewModel.UserListViewModel
import kotlinx.coroutines.launch
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

        getObserver()
        viewModel.fetchUsers()
    }

    private fun getObserver() {
        lifecycleScope.launch {
            viewModel.listUsers.collect { state ->
                when (state) {
                    is UserState.Loading -> {
                        handleLoadingState()
                    }
                    is UserState.ResponseData -> {
                        handleResponseDataState(state.users)
                    }
                    is UserState.Error -> {
                        handleErrorState(state.error)
                    }
                    else -> {
                        handleInactiveState()
                    }
                }
            }
        }

    }

    private fun getState(isLoading: Boolean) {
        binding.apply {
            userListProgressBar.isVisible = isLoading
            recyclerView.isVisible = !isLoading
        }
    }

    private fun renderList(users: List<UsersDomain>?) {
        binding.recyclerView.layoutManager =
            LinearLayoutManager(
                context,
                RecyclerView
                    .VERTICAL,
                false
            )
        val adapter = users?.let { UserListAdapter(it) }
        binding.recyclerView.adapter = adapter
    }

    private fun handleLoadingState() {
        getState(isLoading = true)
    }

    private fun handleResponseDataState(users: List<UsersDomain>?) {
        getState(isLoading = false)
        renderList(users)
    }

    private fun handleErrorState(error: String?) {
        getState(isLoading = false)
        Toast.makeText(requireContext(), error, Toast.LENGTH_LONG).show()
    }

    private fun handleInactiveState() {
        Log.d("Inactive", "Initial state of StateFlow")
    }


//    private fun setView(users: List<User>) {
//        binding.userListProgressBar.visibility = View.GONE
//        val adapter = UserListAdapter(users)
//        binding.recyclerView.adapter = adapter
//        binding.recyclerView.layoutManager = LinearLayoutManager(context)
//    }
//
////    private fun setError() {
////        val message = getString(R.string.error)
////
////        binding.userListProgressBar.visibility = View.GONE
////        binding.recyclerView.visibility = View.GONE
////
////        Toast.makeText(context, message, Toast.LENGTH_SHORT)
////            .show()
////    }
}