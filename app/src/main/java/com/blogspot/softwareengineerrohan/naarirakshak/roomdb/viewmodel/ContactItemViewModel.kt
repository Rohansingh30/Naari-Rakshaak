package com.blogspot.softwareengineerrohan.naarirakshak.roomdb.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blogspot.softwareengineerrohan.naarirakshak.Ui.Activities.MenusWork.CreateContactActivity
import com.blogspot.softwareengineerrohan.naarirakshak.roomdb.Folder.ContactItemModel
import com.blogspot.softwareengineerrohan.naarirakshak.roomdb.repository.ContactItemRepo
import kotlinx.coroutines.launch

class ContactItemViewModel:ViewModel() {


    val repo = ContactItemRepo()
    val list = MutableLiveData<List<ContactItemModel>>()

    fun insertContact(context: CreateContactActivity, contactItemModel: ContactItemModel){
     viewModelScope.launch {
         repo.insertContact(context,contactItemModel)
     } }

    fun deleteContact(context: Context, contactItemModel: ContactItemModel){
        viewModelScope.launch {
            repo.deleteContact(context,contactItemModel)
        } }
    fun getAllContacts(context: Context, contactItemModel: ContactItemModel){
        viewModelScope.launch {
           list.value = repo.getAllContacts(context)
        } }



}