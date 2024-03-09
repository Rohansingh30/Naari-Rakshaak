package com.blogspot.softwareengineerrohan.naarirakshak.roomdb.repository

import android.content.Context
import com.blogspot.softwareengineerrohan.naarirakshak.roomdb.Folder.ContactItemDatabase
import com.blogspot.softwareengineerrohan.naarirakshak.roomdb.Folder.ContactItemModel

class ContactItemRepo {
    suspend fun insertContact(context: Context, contactItemModel: ContactItemModel) {
        ContactItemDatabase.get(context).getContactItemDao().insertContact(contactItemModel)
    }

    suspend fun deleteContact(context: Context, contactItemModel: ContactItemModel) {
        ContactItemDatabase.get(context).getContactItemDao().deleteContact(contactItemModel)
    }

    suspend fun getAllContacts(context: Context): List<ContactItemModel> {
      return  ContactItemDatabase.get(context).getContactItemDao().getAllContact()
    }




}