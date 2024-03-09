package com.blogspot.softwareengineerrohan.naarirakshak.Ui.Activities.MenusWork

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
import com.blogspot.softwareengineerrohan.naarirakshak.roomdb.viewmodel.ContactItemViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CreateContactActivity : AppCompatActivity(), ContactItemAdapter.onActionClickListeber {

    val binding by lazy {
        ActivityCreateContactBinding.inflate(layoutInflater)
    }
    lateinit var viewModel : ContactItemViewModel
    private lateinit var appDb: ContactItemDatabase
//    private lateinit var contactDao: ContactDao
//    private lateinit var contactList: LiveData<List<ContactModel>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel = ContactItemViewModel()


        binding.createBtn.setOnClickListener {
            showInputDialog()


        }
        // to read contact data by rcv ok
//
//        GlobalScope.launch(Dispatchers.IO) {
//            readData()
//
//        }

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

                    val contact = ContactItemModel(name, number)
                    saveInDb(contact)


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


    private fun saveInDb(contact: ContactItemModel) {
 this.let { viewModel.insertContact(this@CreateContactActivity, contact) }
    }

//
//    private fun readData() {
//
//            GlobalScope.launch {
//                val contactRead = appDb.contactItemDao().getAllContact()
//                displayData(contactRead)
//            }
//        }
//
//        @OptIn(DelicateCoroutinesApi::class)
//        private suspend fun displayData(contactRead: List<ContactItemModel>) {
//
//            withContext(Dispatchers.Main) {
//                val adapter = ContactItemAdapter(
//                    this@CreateContactActivity,
//                    contactRead,
//                    this@CreateContactActivity
//                )
//                binding.rcvContactItem.adapter = adapter
//                binding.rcvContactItem.layoutManager =
//                    LinearLayoutManager(this@CreateContactActivity)
//
//
//            }
//
////        private fun saveData() {// Handle OK button click
////            val name = findViewById<android.widget.EditText>(R.id.etU).text.toString()
////            val number = findViewById<android.widget.EditText>(R.id.etP).text.toString()
////
////            if (name.isNotEmpty() && number.isNotEmpty()) {
////                val contact = ContactItemModel(null, name, number)
////                GlobalScope.launch(Dispatchers.IO) {
////                    appDb.contactItemDao().insertContact(contact)
////                }
////                Toast.makeText(this, "Contact saved", Toast.LENGTH_SHORT).show()
////
////            } else {
////
////            }
////        }
//
//        }

    override fun onUpdateClick(position: Int) {
        TODO("Not yet implemented")
    }

    override fun onDeleteClick(position: Int) {
        TODO("Not yet implemented")
    }


}


