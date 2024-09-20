package com.plcoding.auth.data.repository

import com.plcoding.auth.data.RegisterRequest
import com.plcoding.auth.domain.AuthRepository
import com.plcoding.core.data.data.networking.post
import com.plcoding.core.domain.util.DataError
import com.plcoding.core.domain.util.EmptyResult
import io.ktor.client.HttpClient

class AuthRepositoryImpl(
    private val httpClient: HttpClient
): AuthRepository {


    override suspend fun register(email: String, password: String): EmptyResult<DataError.Network> {
        return httpClient.post<RegisterRequest, Unit>(
            route = "/register",
            body = RegisterRequest(
                email = email,
                password = password
            )
        )
    }
}