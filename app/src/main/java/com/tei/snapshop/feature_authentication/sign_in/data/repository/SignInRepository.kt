package com.tei.snapshop.feature_authentication.sign_in.data.repository

import com.tei.snapshop.data.di.DispatcherProvider
import com.tei.snapshop.data.network.APIService
import javax.inject.Inject

/**
 * Class Description
 * Created by Eniola Ipoola on 20/05/2024.
 * Copyright (c). All rights reserved
 */

class SignInRepository @Inject constructor(
    private val apiService: APIService,
    private val dispatcher: DispatcherProvider
){
    /*fun loginUser(email: String, password: String) : Flow<Resource<LoginResponseData>> {
        val loginInfo = LoginData(email, password)
        return flow {
            emit(Resource.Loading())
            val response = cliqboxAPIService.loginUser(loginInfo)
            when {
                response.isSuccessful -> {
                    val data = response.body()
                    emit(Resource.Success(data))
                }
                else -> {
                    if (response.errorBody() == null) {
                        emit(Resource.Error("An error occurred"))
                    } else {
                        val error = response.errorBody()!!.getErrorObject<APIError>().message ?: "An error occurred"
                        emit(Resource.Error(error))
                    }
                }
            }
        }.flowOn(dispatcher.io)
    }
*/
}