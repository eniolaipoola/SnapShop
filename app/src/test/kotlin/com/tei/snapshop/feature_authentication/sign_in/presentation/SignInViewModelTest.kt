package com.tei.snapshop.feature_authentication.sign_in.presentation

import android.content.SharedPreferences
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.gson.Gson
import com.tei.snapshop.data.di.DispatcherProvider
import com.tei.snapshop.feature_authentication.sign_in.presentation.ui.SignInState
import io.mockk.MockKAnnotations
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.unmockkAll
import junit.framework.TestCase
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

/**
 * Class Description
 * Created by Eniola Ipoola on 14/08/2024.
 * Copyright (c). All rights reserved
 */

@ExperimentalCoroutinesApi
class SignInViewModelTest {
    @MockK
    private lateinit var sharedPreferences: SharedPreferences

    @MockK
    private lateinit var dispatcher: DispatcherProvider

    @MockK
    private lateinit var firebaseAuth: FirebaseAuth

    @MockK
    private lateinit var gson: Gson

    private lateinit var viewModel: SignInViewModel

    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxed = true)

        Dispatchers.setMain(testDispatcher)

        viewModel = SignInViewModel(dispatcher, firebaseAuth, sharedPreferences, gson)
    }
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        unmockkAll()
    }

    @Test
    fun `onEmailChanged updates email and trigger validation`() {
        viewModel.onEmailChanged("eniolaipoola@gmail.com")
        TestCase.assertEquals("eniolaipoola@gmail.com", viewModel.email.value)
    }


    @Test
    fun `onPasswordChanged updates password`() {
        viewModel.onPasswordChanged("password123")
        TestCase.assertEquals("password123", viewModel.password.value)
    }

    @Test
    fun `signInUser and set sign-in state to success`() = runTest {
        //Create mock
        val mockAuthResult = mockk<AuthResult>(relaxed = true)
        val mockTask = mockk<Task<AuthResult>>(relaxed = true)

        //Mock all behaviours
        mockTask.apply {
            every { firebaseAuth.signInWithEmailAndPassword(any(), any()) } returns mockTask
            every { isSuccessful } returns true
            every { result } returns mockAuthResult
        }

        //Call the signUpUser method
        viewModel.signInUser("testuser@gmail.com", "password")

        //Verify that the signin method was called with correct parameters
        coVerify { firebaseAuth.signInWithEmailAndPassword("testuser@gmail.com", "password") }

        assert(viewModel.signInState.value is SignInState.Loading)  //assert that the signupState is initially set to Loading

        assertNotNull(viewModel.signInState.value)
    }
}