package com.jobrapp.androidinterview.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.jobrapp.androidinterview.databinding.RvItemUserBinding
import com.jobrapp.androidinterview.extensions.loadImg
import com.jobrapp.server.User

class UserRecyclerViewAdapter(private var items: ArrayList<User>,
                              private var listener: OnItemClickListener)
    : RecyclerView.Adapter<UserRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val binding = RvItemUserBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position], listener)

    override fun getItemCount(): Int = items.size

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun replaceData(newItems: ArrayList<User>){ items = newItems }

    class ViewHolder(private var binding: RvItemUserBinding) :
            RecyclerView.ViewHolder(binding.root) {
        fun bind(repo: User, listener: OnItemClickListener?) {
            binding.user = repo
            binding.userImage.loadImg(repo.profile_url)

            if (listener != null) {
               // TODO("not implemented") // implement on click listener
            }

            binding.executePendingBindings()
        }
    }
}