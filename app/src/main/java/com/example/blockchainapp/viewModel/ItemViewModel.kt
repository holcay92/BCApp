package com.example.blockchainapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.blockchainapp.db.ItemDao
import com.example.blockchainapp.db.UserData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ItemViewModel @Inject constructor(private val dao: ItemDao) : ViewModel() {

    var itemList: LiveData<List<UserData>> = dao.getAllItems()

    suspend fun saveButton(item: UserData) {
        coroutineScope { dao.insertItem(item) }
    }

    suspend fun deleteButton(itemId: Int) {
        val item = coroutineScope {
            dao.getItem(itemId)
        }
        item.let {
            dao.deleteItem(it)
        }
    }

    private val _item = MutableLiveData<UserData?>()
    val item: LiveData<UserData?> get() = _item

    fun getItemById(itemId: Int) {
        viewModelScope.launch {
            // Fetch item in the background
            _item.value = withContext(Dispatchers.IO) {
                dao.getItem(itemId)
            }
        }
    }
}
