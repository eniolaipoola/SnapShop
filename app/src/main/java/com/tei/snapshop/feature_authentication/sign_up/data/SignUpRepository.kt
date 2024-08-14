package com.tei.snapshop.feature_authentication.sign_up.data

import com.tei.snapshop.data.di.DispatcherProvider
import com.tei.snapshop.data.network.APIService
import javax.inject.Inject

/**
 * Repository class for SignUp feature
 * Created by Eniola Ipoola on 10/08/2024.
 * Copyright (c). All rights reserved
 */
class SignUpRepository @Inject constructor(
    private val apiService: APIService,
    private val dispatcher: DispatcherProvider
)