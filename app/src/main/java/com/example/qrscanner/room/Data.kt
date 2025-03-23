package com.example.qrscanner.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "barcode_table")
data class Data (
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val url : String,
//    val barCodeImg : String = ""
)