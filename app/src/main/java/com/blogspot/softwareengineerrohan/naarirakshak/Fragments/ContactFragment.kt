package com.blogspot.softwareengineerrohan.naarirakshak.Fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.blogspot.softwareengineerrohan.naarirakshak.FragmentsAdapters.ContactaAdapter
import com.blogspot.softwareengineerrohan.naarirakshak.FragmentsModels.ContactModel
import com.blogspot.softwareengineerrohan.naarirakshak.databinding.FragmentContactBinding
import com.blogspot.softwareengineerrohan.naarirakshak.roomdb.NaariRakshakDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ContactFragment : Fragment() {
private lateinit var  binding :FragmentContactBinding
// listContacts works
private val listContacts = ArrayList<ContactModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // Inflate the layout for this fragment

        binding = FragmentContactBinding.inflate(inflater, container, false)

        return binding.root


    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    binding.tvv.setOnClickListener {
        Toast.makeText(requireContext(), "Coming Soon", Toast.LENGTH_SHORT).show()
    }



//        val inviteAdapter = ContactaAdapter(listContacts)
//        val listContacts = ArrayList<ContactModel>()
//        listContacts.add(ContactModel("rohan", "989"))
//        listContacts.add(ContactModel("rohan", "989"))
//        listContacts.add(ContactModel("rohan", "989"))
//        listContacts.add(ContactModel("rohan", "989"))
//        listContacts.add(ContactModel("rohan", "989"))
//        listContacts.add(ContactModel("rohan", "989"))
//Coroutine work
        binding.rcvContactInvite.adapter = ContactaAdapter(listContacts)
        binding.rcvContactInvite.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)


        CoroutineScope(Dispatchers.IO).launch{
            listContacts.addAll(fetchContacts())

            insertDatabaseContacts(listContacts)

            withContext(Dispatchers.Main){
                binding.rcvContactInvite.adapter = ContactaAdapter(listContacts)
            }
        }
        //fetch contacts from device
//        fetchContacts()
    // used coroutine after this work for faster load data ok upper

// now Room db work starts

//        insertDatabaseContacts()
















    }

    private suspend fun insertDatabaseContacts(listContacts: ArrayList<ContactModel>) {
        val database = NaariRakshakDatabase.getDatabase(requireActivity())

        database.contactDao().insertAll(listContacts)

//        CoroutineScope(Dispatchers.IO).launch {
//
//        }

    }

    @SuppressLint("Range")
    private fun fetchContacts(): ArrayList<ContactModel> {

        val listContacts = ArrayList<ContactModel>()

        val cr = requireActivity().contentResolver
        val cursor = cr.query(
            ContactsContract.Contacts.CONTENT_URI,null,null,null,null)
        if (cursor!=null&&cursor.count>0){
            while (cursor!=null&&cursor.moveToNext()){
                val id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID))
                val name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                val hasPhoneNumber = cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))
                if(hasPhoneNumber>0){

                    val pCur = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = ?", arrayOf(id),"")
                    if(pCur!=null&&pCur.count>0){
                        while (pCur!=null&&pCur.moveToNext()){
                            val phoneNo = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                            listContacts.add(ContactModel(name, phoneNo))
                        }
                        pCur.close()
                    }

                }
            }
            if (cursor!=null){
                cursor.close()
            }
        }
        return listContacts
    }

}