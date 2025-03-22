package com.example.qrscanner.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.qrscanner.room.BarcodeRepo
import com.example.qrscanner.room.Data
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ScannerViewModel(private val repository: BarcodeRepo) : ViewModel() {
    private val _collectedData = MutableStateFlow<List<Data>>(emptyList())
    val collectedUsers = _collectedData

    init {
        collectBarcodeData()
    }

    fun insertData(data : Data){
        viewModelScope.launch {
            repository.insert(data)
        }
    }

    fun collectBarcodeData(){
        viewModelScope.launch {
            repository.allData.collectLatest { dataList ->
                _collectedData.value = dataList
            }
        }
    }

    fun deleteData(data: Data){
        viewModelScope.launch {
            repository.delete(data)
        }
    }
}