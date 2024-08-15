package com.tei.snapshop.data.di

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import androidx.room.Room
import com.tei.snapshop.data.local.AppDatabase
import com.tei.snapshop.feature_products.data.local.ProductDao
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Class Description
 * Created by Eniola Ipoola on 15/08/2024.
 * Copyright (c). All rights reserved
 */

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun providesDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(app, AppDatabase::class.java, "snapshop_db").build()
    }

    @Provides
    @Singleton
    fun provideProductDao(database: AppDatabase) : ProductDao {
        return database.productDao()
    }
}