package com.picpay.desafio.android.presentation.view.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.picpay.desafio.android.data.models.User
import com.picpay.desafio.android.databinding.ListItemUserBinding

class UserListItemViewHolder(
    private val binding: ListItemUserBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(user: User) {
        with(binding) {
            name.text = user.name
            username.text = user.username
            Glide.with(root.context)
                .load(user.img)
                .into(picture)
        }
    }
}