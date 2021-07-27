package com.mvvmhilt.data.remote

import com.mvvmhilt.data.Resource
import com.mvvmhilt.data.dto.recipes.Recipes

/**
 * Created by navdeepera04
 */

internal interface RemoteDataSource {
    suspend fun requestRecipes(): Resource<Recipes>
}
