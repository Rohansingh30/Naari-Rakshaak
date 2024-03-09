package com.blogspot.softwareengineerrohan.naarirakshak.roomdb.Folder

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(ContactItemModel::class), exportSchema = false,  version = 1)
abstract class ContactItemDatabase: RoomDatabase(){

    abstract fun getContactItemDao(): ContactItemDao

    companion object{
       fun get(context: Context):ContactItemDatabase{
           return Room.databaseBuilder(
               context, ContactItemDatabase::class.java,"contact_database"
           ).build()
       }
    }


}