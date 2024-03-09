package com.blogspot.softwareengineerrohan.naarirakshak.roomdb.Folder

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ContactItemDao {

    @Insert
    suspend fun insertContact(contactItemModel: ContactItemModel)
    @Delete
    suspend fun deleteContact(contactItemModel: ContactItemModel)
    @Query("SELECT * FROM contact_table")
    suspend fun getAllContact():List<ContactItemModel>





//
//    @Query("SELECT * FROM contact_table")
//    fun getAllContact():List<ContactItemModel>
//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    suspend fun insertContact(contactItemModel: ContactItemModel)
//



}