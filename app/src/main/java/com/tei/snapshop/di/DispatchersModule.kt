package com.tei.snapshop.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Class Description
 * Created by Eniola Ipoola on 05/06/2024.
 * Copyright (c). All rights reserved
 */

@Module
@InstallIn(SingletonComponent::class)
internal object DispatchersModule {

    @Provides
    fun providesIODispatcher(): DispatcherProvider = AppDispatchers()

}