package com.example.qrscanner.room

import kotlinx.coroutines.flow.Flow

class BarcodeRepo(private val dao : BarcodeDao) {
    val allData : Flow<List<Data>> = dao.getAllData()

    suspend fun insert(data : Data){
        dao.insert(data)
    }

    suspend fun delete(data: Data){
        dao.delete(data)
    }
}