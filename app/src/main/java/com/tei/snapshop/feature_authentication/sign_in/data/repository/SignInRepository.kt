package com.tei.snapshop.feature_authentication.sign_in.data.repository

import android.content.SharedPreferences
import com.google.gson.Gson
import com.tei.snapshop.feature_authentication.sign_in.data.User
import javax.inject.Inject

/**
 * Class Description
 * Created by Eniola Ipoola on 20/05/2024.
 * Copyright (c). All rights reserved
 */

class SignInRepository @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val gson: Gson
){
    fun getUserData(): User? {
        val userJson = sharedPreferences.getString("user_data", null)
        return gson.fromJson(userJson, User::class.java)
    }
}