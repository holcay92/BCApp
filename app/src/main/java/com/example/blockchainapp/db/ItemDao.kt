package com.example.blockchainapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ItemDao {

    @Insert
    suspend fun insertItem(item: UserData)

    @Update
    suspend fun updateItem(item: UserData): Int

    @Delete
    suspend fun deleteItem(item: UserData): Int

    @Query("SELECT * FROM item_table ORDER BY item_id ASC")
    fun getAllItems(): LiveData<List<UserData>>

    @Query("SELECT * FROM item_table WHERE item_id = :itemId")
    fun getItem(itemId: Int): UserData
}
