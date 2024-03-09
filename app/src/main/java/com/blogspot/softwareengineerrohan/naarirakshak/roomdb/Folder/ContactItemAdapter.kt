package com.blogspot.softwareengineerrohan.naarirakshak.roomdb.Folder

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.blogspot.softwareengineerrohan.naarirakshak.databinding.RcvContactItemBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ContactItemAdapter(val context : Context, var list : List<ContactItemModel>, val listener: onActionClickListeber):RecyclerView.Adapter<ContactItemAdapter.ViewHolder>() {
    class ViewHolder(val binding : RcvContactItemBinding):RecyclerView.ViewHolder(binding.root) {

    }
fun setContactList(list : List<ContactItemModel>){
    this.list = list
    notifyDataSetChanged()
}

    interface onActionClickListeber {
        fun onUpdateClick(position: Int)
         fun onDeleteClick(position: Int)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RcvContactItemBinding.inflate(
            LayoutInflater.from(parent.context),parent,false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
    return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = list[position]
        holder.binding.tvName.text = item.name
        holder.binding.tvNumber.text = item.number

        holder.binding.tvDeleteBtn.setOnClickListener {

            listener.onDeleteClick(position)
        }
        holder.binding.tvUpdateBtn.setOnClickListener {

            listener.onUpdateClick(position)
        }
    }
}