package com.example.blockchainapp.viewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.blockchainapp.db.Item
import com.example.blockchainapp.db.ItemDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemViewModel @Inject constructor(private val dao: ItemDao) : ViewModel() {

    val items: LiveData<List<Item>> = dao.getAllItems()

    fun insert(item: Item) = viewModelScope.launch {
        dao.insertItem(item)
    }

    fun update(item: Item) = viewModelScope.launch {
        dao.updateItem(item)
    }

    fun delete(item: Item) = viewModelScope.launch {
        dao.deleteItem(item)
    }
}