package com.blogspot.softwareengineerrohan.naarirakshak.roomdb.Folder

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contact_table")
data class ContactItemModel(

    val name: String,
    val number: String
) {
    @PrimaryKey(autoGenerate = true) var id = 0
}