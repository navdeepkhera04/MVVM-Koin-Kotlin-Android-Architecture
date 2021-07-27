package com.mvvmhilt.ui.base.listeners

import com.mvvmhilt.data.dto.recipes.RecipesItem

/**
 * Created by navdeepera04
 */

interface RecyclerItemListener {
    fun onItemSelected(recipe : RecipesItem)
}
