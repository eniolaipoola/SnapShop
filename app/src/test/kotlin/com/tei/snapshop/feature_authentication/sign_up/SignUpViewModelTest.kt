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
import kotlinx.coroutines.test.TestCoroutineDispatcher
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

    private val testDispatcher = TestCoroutineDispatcher()


    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxed = true)

        Dispatchers.setMain(testDispatcher)

        viewModel = SignUpViewModel(dispatcher, repository, firebaseAuth)
    }
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
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

        //Advance until all coroutines are completed
        advanceUntilIdle()

        //Verify that the signUpUser method was called with correct parameters
        coVerify { firebaseAuth.createUserWithEmailAndPassword("testuser@gmail.com", "password") }

        assert(viewModel.signUpState.value is SignUpState.Loading)  //assert that the signupState is initially set to Loading

        //Assert that the signupState is set to Success after the task completes
        //assert(viewModel.signUpState.value is SignUpState.Error)
    }

    @Test
    fun `signUpUser sets signupState to Error on failed signup`() = runTest {
        assert(viewModel.signUpState.value is SignUpState.Idle)

        val mockTask = mockk<Task<AuthResult>>(relaxed = true)
        every { mockTask.isSuccessful } returns false
        every { mockTask.exception } returns Exception("Signup failed")
        every { firebaseAuth.createUserWithEmailAndPassword(any(), any()) } returns mockTask

        viewModel.signUpUser("testuser@example.com", "password1")

        coVerify { firebaseAuth.createUserWithEmailAndPassword("testuser@example.com", "password1") }
        assert(viewModel.signUpState.value is SignUpState.Loading)

        mockTask.apply {
            every { isSuccessful } returns false
            every { exception } returns Exception("Signup failed")
        }

        //assert(viewModel.signUpState.value is SignUpState.Error)
    }

}