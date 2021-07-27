package com.mvvmhilt.data

import com.mvvmhilt.data.dto.recipes.Recipes
import com.mvvmhilt.data.dto.login.LoginRequest
import com.mvvmhilt.data.dto.login.LoginResponse
import kotlinx.coroutines.flow.Flow

/**
 * Created by navdeepera04
 */

interface DataRepositorySource {
    suspend fun requestRecipes(): Flow<Resource<Recipes>>
    suspend fun doLogin(loginRequest: LoginRequest): Flow<Resource<LoginResponse>>
    suspend fun addToFavourite(id: String): Flow<Resource<Boolean>>
    suspend fun removeFromFavourite(id: String): Flow<Resource<Boolean>>
    suspend fun isFavourite(id: String): Flow<Resource<Boolean>>
}
