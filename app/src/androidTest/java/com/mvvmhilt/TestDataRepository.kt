package com.mvvmhilt

import com.mvvmhilt.TestUtil.dataStatus
import com.mvvmhilt.TestUtil.initData
import com.mvvmhilt.data.DataRepositorySource
import com.mvvmhilt.data.Resource
import com.mvvmhilt.data.dto.login.LoginRequest
import com.mvvmhilt.data.dto.login.LoginResponse
import com.mvvmhilt.data.dto.recipes.Recipes
import com.mvvmhilt.data.error.Error
import com.mvvmhilt.data.error.NETWORK_ERROR
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


/**
 * Created by navdeepera04
 */

class TestDataRepository @Inject constructor() : DataRepositorySource {

    override suspend fun requestRecipes(): Flow<Resource<Recipes>> {
        return when (dataStatus) {
            DataStatus.Success -> {
                flow { emit(Resource.Success(initData())) }
            }
            DataStatus.Fail -> {
                flow { emit(Resource.DataError<Recipes>(errorCode = NETWORK_ERROR)) }
            }
            DataStatus.EmptyResponse -> {
                flow { emit(Resource.Success(Recipes(arrayListOf()))) }
            }
        }
    }

    override suspend fun doLogin(loginRequest: LoginRequest): Flow<Resource<LoginResponse>> {
        return flow {
            emit(Resource.Success(LoginResponse("123", "Navdeep", "Khera",
                    "chiyoda", "25", "4500001", "Aichi",
                    "Japan", "navdeepkhera04@gmail.com")))
        }
    }

    override suspend fun addToFavourite(id: String): Flow<Resource<Boolean>> {
        return flow { emit(Resource.Success(true)) }
    }

    override suspend fun removeFromFavourite(id: String): Flow<Resource<Boolean>> {
        return flow { emit(Resource.Success(true)) }
    }

    override suspend fun isFavourite(id: String): Flow<Resource<Boolean>> {
        return flow { emit(Resource.Success(true)) }
    }
}
