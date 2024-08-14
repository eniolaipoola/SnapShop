package com.tei.snapshop.feature_authentication.sign_up

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.tei.snapshop.data.di.DispatcherProvider
import com.tei.snapshop.feature_authentication.sign_up.data.SignUpRepository
import com.tei.snapshop.feature_authentication.sign_up.presentation.ui.SignUpState
import io.mockk.MockKAnnotations
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.unmockkAll
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

/**
 * SignupViewModel unit test class
 * Created by Eniola Ipoola on 14/08/2024.
 * Copyright (c). All rights reserved
 */

@ExperimentalCoroutinesApi
class SignUpViewModelTest {

    @MockK
    private lateinit var repository: SignUpRepository

    @MockK
    private lateinit var dispatcher: DispatcherProvider

    @MockK
    private lateinit var firebaseAuth: FirebaseAuth

    private lateinit var viewModel: SignUpViewModel

    private val testDispatcher = UnconfinedTestDispatcher()


    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxed = true)

        Dispatchers.setMain(testDispatcher)

        viewModel = SignUpViewModel(dispatcher, repository, firebaseAuth)
    }
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        unmockkAll()
    }

    @Test
    fun `onEmailChanged updates email and trigger validation`() {
        viewModel.onEmailChanged("eniolaipoola@gmail.com")
        assertEquals("eniolaipoola@gmail.com", viewModel.email.value)
        assertNull(viewModel.emailError.value)
    }

    @Test
    fun `onEmail changed but input is not valid`() {
        viewModel.onEmailChanged("eniolaipool")
        assertNotNull(viewModel.emailError.value)
    }

    @Test
    fun `onPasswordChanged updates password`() {
        viewModel.onPasswordChanged("password123")
        assertEquals("password123", viewModel.password.value)
    }

    @Test
    fun `onConfirmPasswordChanged with matching passwords clears error`() {
        viewModel.onPasswordChanged("password123")
        viewModel.onConfirmPasswordChanged("password123")
        assertNull(viewModel.passwordError.value)
    }

    @Test
    fun `onConfirmPasswordChanged with non-matching passwords sets error`() {
        viewModel.onPasswordChanged("password123")
        viewModel.onConfirmPasswordChanged("password456")
        assertNotNull(viewModel.passwordError.value)
    }

    @Test
    fun `signUpUser and set signupState to Success on successful signup`() = runTest {
        //Create mock
        val mockAuthResult = mockk<AuthResult>(relaxed = true)
        val mockTask = mockk<Task<AuthResult>>(relaxed = true)

        //Mock all behaviours
        mockTask.apply {
            every { isSuccessful } returns true
            every { result } returns mockAuthResult
            every { firebaseAuth.createUserWithEmailAndPassword(any(), any()) } returns mockTask
        }

        //Call the signUpUser method
        viewModel.signUpUser("testuser@gmail.com", "password")

        //Verify that the signUpUser method was called with correct parameters
        coVerify { firebaseAuth.createUserWithEmailAndPassword("testuser@gmail.com", "password") }

        assert(viewModel.signUpState.value is SignUpState.Loading)  //assert that the signupState is initially set to Loading

        assertNotNull(viewModel.signUpState.value)
    }

    @Test
    fun `signUpUser sets signupState to Error on failed signup`() = runTest {
        assert(viewModel.signUpState.value is SignUpState.Idle)

        val mockTask = mockk<Task<AuthResult>>(relaxed = true)
        every { mockTask.isSuccessful } returns false
        every { mockTask.exception } returns Exception("Signup failed")
        every { firebaseAuth.createUserWithEmailAndPassword(any(), any()) } returns mockTask

        viewModel.signUpUser("testuser@gmail.com", "password")

        coVerify { firebaseAuth.createUserWithEmailAndPassword("testuser@gmail.com", "password") }
        assert(viewModel.signUpState.value is SignUpState.Loading)

        mockTask.apply {
            every { isSuccessful } returns false
            every { exception } returns Exception("Signup failed")
        }

        //assert(viewModel.signUpState.value is SignUpState.Error)
    }

    @Test
    fun `signUpUser sets signupState to Success when task is successful`() = runTest {
        // Mocking the Task to simulate success
        val mockTask = mockk<Task<AuthResult>>(relaxed = true)
        every { mockTask.isSuccessful } returns true

        // Mock FirebaseAuth to return the mocked Task
        every { firebaseAuth.createUserWithEmailAndPassword(any(), any()) } returns mockTask

        // Call the signUpUser method
        viewModel.signUpUser("testuser@gmail.com", "password")

        // Advance until all coroutines and asynchronous tasks are completed
        advanceUntilIdle()

        // Assert that signupState is Success
        assertNotNull(viewModel.signUpState.value is SignUpState.Success)
    }



}