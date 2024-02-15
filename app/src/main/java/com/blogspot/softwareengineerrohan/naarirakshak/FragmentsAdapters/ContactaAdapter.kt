package com.blogspot.softwareengineerrohan.naarirakshak.FragmentsAdapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.blogspot.softwareengineerrohan.naarirakshak.FragmentsModels.ContactModel
import com.blogspot.softwareengineerrohan.naarirakshak.databinding.RcvContactsInviteBinding

class ContactaAdapter(private val contactList: ArrayList<ContactModel>): RecyclerView.Adapter<ContactaAdapter.ViewHolder>() {
    class ViewHolder(val binding: RcvContactsInviteBinding): RecyclerView.ViewHolder(binding.root) {


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RcvContactsInviteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
return contactList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val listContacts = contactList[position]
        holder.binding.inviteName.text = listContacts.name




    }
}