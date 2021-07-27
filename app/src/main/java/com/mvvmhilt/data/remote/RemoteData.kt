package com.mvvmhilt.data.remote

import com.mvvmhilt.data.Resource
import com.mvvmhilt.data.dto.recipes.Recipes
import com.mvvmhilt.data.dto.recipes.RecipesItem
import com.mvvmhilt.data.error.NETWORK_ERROR
import com.mvvmhilt.data.error.NO_INTERNET_CONNECTION
import com.mvvmhilt.data.remote.service.RecipesService
import com.mvvmhilt.utils.NetworkConnectivity
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject


/**
 * Created by navdeepera04
 */

class RemoteData @Inject
constructor(private val serviceGenerator: ServiceGenerator, private val networkConnectivity: NetworkConnectivity) : RemoteDataSource {
    override suspend fun requestRecipes(): Resource<Recipes> {
        val recipesService = serviceGenerator.createService(RecipesService::class.java)
        return when (val response = processCall(recipesService::fetchRecipes)) {
            is List<*> -> {
                Resource.Success(data = Recipes(response as ArrayList<RecipesItem>))
            }
            else -> {
                Resource.DataError(errorCode = response as Int)
            }
        }
    }

    private suspend fun processCall(responseCall: suspend () -> Response<*>): Any? {
        if (!networkConnectivity.isConnected()) {
            return NO_INTERNET_CONNECTION
        }
        return try {
            val response = responseCall.invoke()
            val responseCode = response.code()
            if (response.isSuccessful) {
                response.body()
            } else {
                responseCode
            }
        } catch (e: IOException) {
            NETWORK_ERROR
        }
    }
}
