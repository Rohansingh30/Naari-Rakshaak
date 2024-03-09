package com.blogspot.softwareengineerrohan.naarirakshak.MenusWork

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.blogspot.softwareengineerrohan.naarirakshak.R
import com.blogspot.softwareengineerrohan.naarirakshak.databinding.ActivityCreateContactBinding
import com.blogspot.softwareengineerrohan.naarirakshak.roomdb.Folder.ContactItemAdapter
import com.blogspot.softwareengineerrohan.naarirakshak.roomdb.Folder.ContactItemDatabase
import com.blogspot.softwareengineerrohan.naarirakshak.roomdb.Folder.ContactItemModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CreateContactActivity : AppCompatActivity(), ContactItemAdapter.onActionClickListeber {

    val binding by lazy {
        ActivityCreateContactBinding.inflate(layoutInflater)
    }
    private lateinit var appDb: ContactItemDatabase
//    private lateinit var contactDao: ContactDao
//    private lateinit var contactList: LiveData<List<ContactModel>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        appDb = ContactItemDatabase.getDatabase(this)


        binding.createBtn.setOnClickListener {
            showInputDialog()


        }
        // to read contact data by rcv ok

        GlobalScope.launch(Dispatchers.IO) {
            readData()

        }

//            binding.saveBtn.setOnClickListener {
//                saveData()
//we used alternate way to save data using alert dialog box
//
//            }

//
//            binding.readBtn.setOnClickListener {
//                GlobalScope.launch(Dispatchers.IO) {
//                    readData()
//                }
//            }

    }

    private fun showInputDialog() {
        // Example: Accessing input data
        // Process input data

        val builder = AlertDialog.Builder(this)
        val inflater = LayoutInflater.from(this)
        val view = inflater.inflate(R.layout.dialog_contact_add, null)
        builder.setView(view)
            .setTitle("Enter Contact Details")
            .setPositiveButton("OK") { dialog, which ->
                // Handle OK button click
                val name = view.findViewById<android.widget.EditText>(R.id.etU).text.toString()
                val number = view.findViewById<android.widget.EditText>(R.id.etP).text.toString()

                if (name.isNotEmpty() && number.isNotEmpty()) {
                    val contact = ContactItemModel(null, name, number)
                    GlobalScope.launch(Dispatchers.IO) {
                        appDb.contactItemDao().insertContact(contact)
                    }
                    Toast.makeText(this, "Contact saved", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this, "Please enter name and number", Toast.LENGTH_SHORT).show()
                }

            }
            .setNegativeButton("Cancel") { dialog, which ->
                // Handle Cancel button click
                dialog.dismiss()
            }
            .show()
    }


    private fun readData() {

            GlobalScope.launch {
                val contactRead = appDb.contactItemDao().getAllContact()
                displayData(contactRead)
            }
        }

        @OptIn(DelicateCoroutinesApi::class)
        private suspend fun displayData(contactRead: List<ContactItemModel>) {



            withContext(Dispatchers.Main) {
                binding.rcvContactItem.adapter = ContactItemAdapter(
                    this@CreateContactActivity,
                    contactRead,
                    this@CreateContactActivity
                )
//                ContactItemAdapter (this@CreateContactActivity, contactRead, this@CreateContactActivity)
                binding.swipeLayout.setOnRefreshListener {
                    GlobalScope.launch(Dispatchers.IO) {
                        val contactRead = appDb.contactItemDao().getAllContact()
                        displayData(contactRead)

                    }

                    binding.swipeLayout.isRefreshing = false
                    binding.rcvContactItem.layoutManager =
                        LinearLayoutManager(this@CreateContactActivity)
                    binding.rcvContactItem.setHasFixedSize(true)
                    binding.rcvContactItem.adapter?.notifyDataSetChanged()



                }


            }



        }

//        private fun saveData() {// Handle OK button click
//            val name = findViewById<android.widget.EditText>(R.id.etU).text.toString()
//            val number = findViewById<android.widget.EditText>(R.id.etP).text.toString()
//
//            if (name.isNotEmpty() && number.isNotEmpty()) {
//                val contact = ContactItemModel(null, name, number)
//                GlobalScope.launch(Dispatchers.IO) {
//                    appDb.contactItemDao().insertContact(contact)
//                }
//                Toast.makeText(this, "Contact saved", Toast.LENGTH_SHORT).show()
//
//            } else {
//
//            }
//        }

        override fun onUpdateClick(position: Int) {
            Toast.makeText(this, "Function not implemented", Toast.LENGTH_SHORT).show()
//        GlobalScope.launch {
//            appDb.contactItemDao().updateContact(appDb.contactItemDao().getAllContact()[position])
//        }

        }

        override fun onDeleteClick(position: Int) {

            GlobalScope.launch {
                appDb.contactItemDao()
                    .deleteContact(appDb.contactItemDao().getAllContact()[position])
            }
            Toast.makeText(this, "Contact deleted", Toast.LENGTH_SHORT).show()

        }


    }

