package com.example.retrofitdemo

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.retrofitdemo.databinding.CardviewLayoutBinding
import com.example.retrofitdemo.model.User

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    var users = emptyList<User>()
    private lateinit var context: Context

    fun setData(context: Context, users:List<User>){
        this.context = context
        this.users = users

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewAdapter.ViewHolder {
        val view = CardviewLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerViewAdapter.ViewHolder, position: Int) {

        holder.binding.id.text = users[position].id.toString()
        holder.binding.name.text = users[position].name
        holder.binding.email.text = users[position].email
        holder.binding.gender.text = users[position].gender
        holder.binding.status.text = users[position].status

    }

    override fun getItemCount(): Int {
        return users.size
    }

    class ViewHolder(val binding: CardviewLayoutBinding): RecyclerView.ViewHolder(binding.root)  {

    }
}