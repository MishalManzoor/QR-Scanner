package com.example.qrscanner.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.qrscanner.room.BarcodeRepo

class ViewModelFactory(private val repo : BarcodeRepo) : ViewModelProvider.Factory{
    override fun<T: ViewModel> create(modelClass : Class<T>) : T{
        if (modelClass.isAssignableFrom(ScannerViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ScannerViewModel(repo) as T
        }
        throw IllegalArgumentException("unknows ViewModel class")
    }
}
/*
class UserViewModelFactory(private val repository: UserRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UserViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
 */