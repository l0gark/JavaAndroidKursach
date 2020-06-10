package com.loginov.hospital.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class RecyclerAdapter<T> : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    private val items = mutableListOf<T>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(itemLayoutId(), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        bind(holder.itemView, items[position])
    }

    abstract fun itemLayoutId(): Int

    abstract fun bind(itemView: View, item: T)

    fun bindData(items: List<T>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}