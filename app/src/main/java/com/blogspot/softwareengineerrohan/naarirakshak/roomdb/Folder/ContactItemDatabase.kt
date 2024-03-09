package com.blogspot.softwareengineerrohan.naarirakshak.roomdb.Folder

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ContactItemModel::class], version = 1)
abstract class ContactItemDatabase: RoomDatabase(){

    abstract fun contactItemDao(): ContactItemDao

    companion object{
        @Volatile
        private var INSTANCE: ContactItemDatabase?=null
        fun getDatabase(context: android.content.Context):ContactItemDatabase{
            val tempInstance = INSTANCE
            if(tempInstance!=null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ContactItemDatabase::class.java,
                    "contact_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }


}