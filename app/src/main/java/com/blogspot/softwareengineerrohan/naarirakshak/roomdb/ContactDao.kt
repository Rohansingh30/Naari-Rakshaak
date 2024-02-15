package com.blogspot.softwareengineerrohan.naarirakshak.roomdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.blogspot.softwareengineerrohan.naarirakshak.FragmentsModels.ContactModel

@Dao
interface ContactDao {

@Insert(onConflict = OnConflictStrategy.REPLACE)
suspend fun insertContact(contactModel: kotlin.collections.ArrayList<ContactModel>)


@Insert(onConflict = OnConflictStrategy.REPLACE)
suspend fun insertAll(contactModel: List<ContactModel>)


@Query("SELECT * FROM ContactModel")
suspend fun getAllContact(): List<ContactModel>



}