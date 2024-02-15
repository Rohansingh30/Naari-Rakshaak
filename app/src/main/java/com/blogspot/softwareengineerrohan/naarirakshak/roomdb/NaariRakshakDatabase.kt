package com.blogspot.softwareengineerrohan.naarirakshak.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.blogspot.softwareengineerrohan.naarirakshak.FragmentsModels.ContactModel

@Database(entities = [ContactModel::class], version = 1, exportSchema = false)
abstract class NaariRakshakDatabase: RoomDatabase() {


    abstract fun contactDao(): ContactDao


    companion object {
        @Volatile
        private var INSTANCE: NaariRakshakDatabase? = null

        fun getDatabase(context: Context): NaariRakshakDatabase {
            // if the INSTANCE is not null, then return it,
            INSTANCE?.let {
                return it
            }
            // if it is, then create the database
            return synchronized(NaariRakshakDatabase::class.java) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NaariRakshakDatabase::class.java,
                    "naari_rakshak_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }

    }
}