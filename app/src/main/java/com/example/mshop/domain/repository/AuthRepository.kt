package com.example.mshop.domain.repository

import com.sudo248.base_android.core.DataState
import com.example.mshop.domain.entity.auth.Account
import com.example.mshop.domain.entity.auth.Token


/**
 * **Created by**
 *
 * @author *Sudo248*
 * @since 23:47 - 04/03/2023
 */
interface AuthRepository {
    suspend fun refreshToken(): DataState<Token, Exception>
    suspend fun saveToken(token: Token): DataState<Unit, Exception>
    suspend fun signIn(account: Account): DataState<Token, Exception>
    suspend fun signUp(account: Account): DataState<Unit, Exception>
    suspend fun generateOtp(phoneNumber: String): DataState<Unit, Exception>
    suspend fun verifyOtp(phoneNumber: String, otp: String): DataState<Token, Exception>
    suspend fun changePassword(oldPassword: String, newPassWord: String): DataState<Unit, Exception>
    suspend fun logout(): DataState<Unit, Exception>
}