package com.example.blockchainapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.blockchainapp.db.ItemDao
import com.example.blockchainapp.db.UserData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemViewModel @Inject constructor(private val dao: ItemDao) : ViewModel() {

    var itemList: LiveData<List<UserData>> = dao.getAllItems()

    fun saveButton(item: UserData) = viewModelScope.launch {
        dao.insertItem(item)
    }
}
