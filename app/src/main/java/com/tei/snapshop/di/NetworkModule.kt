package com.tei.snapshop.di

import com.tei.snapshop.network.APIService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber

/**
 * Class Description
 * Created by Eniola Ipoola on 05/06/2024.
 * Copyright (c). All rights reserved
 */

@Module
@InstallIn(ViewModelComponent::class)
class NetworkModule {

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor { message ->
            Timber.d(message)
        }.apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://api.stage.ajobox.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    @Provides
    fun provideSnapShopService(retrofit: Retrofit): APIService {
        return retrofit.create(APIService::class.java)
    }
}