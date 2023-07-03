package com.picpay.desafio.android.users.presentation.view.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.picpay.desafio.android.databinding.ListItemUserBinding
import com.picpay.desafio.android.users.domain.models.UsersDomain

class UserListItemViewHolder(
    private val binding: ListItemUserBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(user: UsersDomain) {
        with(binding) {
            name.text = user.name
            username.text = user.username
            Glide.with(root.context)
                .load(user.img)
                .into(picture)
        }
    }
}