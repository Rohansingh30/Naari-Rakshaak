//package com.blogspot.softwareengineerrohan.naarirakshak.roomdb
//
//import androidx.lifecycle.LiveData
//import androidx.room.Dao
//import androidx.room.Insert
//import androidx.room.OnConflictStrategy
//import androidx.room.Query
//import com.blogspot.softwareengineerrohan.naarirakshak.FragmentsModels.ContactModel
//
//@Dao
//interface ContactDao {
//
// @Query("SELECT * FROM contact_table")
// fun getAllContact(): LiveData<List<ContactModel>>
// @Insert(onConflict = OnConflictStrategy.IGNORE)
// suspend fun insertContact(contactModel: com.blogspot.softwareengineerrohan.naarirakshak.FragmentsModels.ContactModel)
//
//
//
//
//}