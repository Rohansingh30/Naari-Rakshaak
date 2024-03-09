package com.blogspot.softwareengineerrohan.naarirakshak.HeaderAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.blogspot.softwareengineerrohan.naarirakshak.HeaderFragments.InviteFragment
import com.blogspot.softwareengineerrohan.naarirakshak.databinding.RcvInviteLayoutBinding

class InviteAdapter(val context: Context, val inviteList: ArrayList<String>, val onActionClickListener: OnActionClickListener):RecyclerView.Adapter<InviteAdapter.ViewHolder>() {


    interface OnActionClickListener{

        fun onAcceptClick(inviteEmail: String)
        fun onDenyClick(inviteEmail: String)


    }



    class ViewHolder(val binding : RcvInviteLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding = RcvInviteLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
    return inviteList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
val item = inviteList[position]
        holder.binding.tvReceiveEmail.text = item

        holder.binding.tvAccept.setOnClickListener {
            onActionClickListener.onAcceptClick(item)
        }
        holder.binding.tvDeny.setOnClickListener {
            onActionClickListener.onDenyClick(item)
        }


    }
}