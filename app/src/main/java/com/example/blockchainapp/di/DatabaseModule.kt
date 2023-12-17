package com.example.blockchainapp.di

import android.content.Context
import androidx.room.Room
import com.example.blockchainapp.db.ItemDao
import com.example.blockchainapp.db.ItemDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideItemDatabase(@ApplicationContext context: Context): ItemDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            ItemDatabase::class.java,
            "movie_data_table",
        ).build()
    }

    @Provides
    fun provideDao(itemDatabase: ItemDatabase): ItemDao {
        return itemDatabase.dao()
    }
}
