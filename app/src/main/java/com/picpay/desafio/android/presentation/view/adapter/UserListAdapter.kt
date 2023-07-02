package com.picpay.desafio.android.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.databinding.ListItemUserBinding
import com.picpay.desafio.android.domain.models.UsersDomain

class UserListAdapter(
    private var listUsers: List<UsersDomain>
) : RecyclerView.Adapter<UserListItemViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserListItemViewHolder {
        return UserListItemViewHolder(
            ListItemUserBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: UserListItemViewHolder, position: Int) {
        holder.bind(listUsers[position])
    }

    override fun getItemCount(): Int {
        return listUsers.size
    }
}