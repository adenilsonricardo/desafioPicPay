package com.picpay.desafio.android.presentation.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.picpay.desafio.android.data.models.User
import com.picpay.desafio.android.databinding.ListItemUserBinding

class UsersAdapter(
    var listCharacters: List<User>
) : RecyclerView.Adapter<UsersAdapter.ViewHolder>() {

    class ViewHolder(val binding: ListItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(objectListCharacters: User) {
            with(binding) {
                name.text = objectListCharacters.name
                username.text = objectListCharacters.username
                Glide.with(root.context)
                    .load(objectListCharacters.img)
                    .into(picture)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            ListItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val objectListCharacters = listCharacters[position]
        holder.onBind(objectListCharacters)
    }

    override fun getItemCount(): Int {
        return listCharacters.size
    }
}