package com.mvvmhilt.data.remote.service

import com.mvvmhilt.data.dto.recipes.RecipesItem
import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by navdeepera04
 */

interface RecipesService {
    @GET("recipes.json")
    suspend fun fetchRecipes(): Response<List<RecipesItem>>
}
