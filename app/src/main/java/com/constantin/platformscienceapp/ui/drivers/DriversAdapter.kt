package com.constantin.platformscienceapp.ui.drivers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.constantin.platformscienceapp.databinding.ItemDriverBinding
import com.constantin.platformscienceapp.domain.models.Driver

class DriversAdapter(private val onDriverClicked: (Driver) -> Unit) :
    ListAdapter<Driver, DriverViewHolder>(DriverDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DriverViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemDriverBinding.inflate(inflater, parent, false)
        return DriverViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DriverViewHolder, position: Int) {
        holder.bind(getItem(position), onDriverClicked)
    }
}

class DriverViewHolder(private val layoutBinding: ItemDriverBinding) :
    RecyclerView.ViewHolder(layoutBinding.root) {

    fun bind(driver: Driver, onClickListener: (Driver) -> Unit) {
        layoutBinding.tvDriverName.text = driver.name
        itemView.setOnClickListener {
            onClickListener.invoke(driver)
        }
    }
}

class DriverDiffCallback : DiffUtil.ItemCallback<Driver>() {

    override fun areItemsTheSame(oldItem: Driver, newItem: Driver): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Driver, newItem: Driver): Boolean {
        return oldItem.name == newItem.name
    }
}
