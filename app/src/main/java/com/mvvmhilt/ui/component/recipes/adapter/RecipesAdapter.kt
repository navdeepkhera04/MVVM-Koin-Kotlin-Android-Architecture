package com.mvvmhilt.ui.component.recipes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mvvmhilt.data.dto.recipes.RecipesItem
import com.mvvmhilt.databinding.RecipeItemBinding
import com.mvvmhilt.ui.base.listeners.RecyclerItemListener
import com.mvvmhilt.ui.component.recipes.RecipesListViewModel

/**
 * Created by navdeepera04
 */

class RecipesAdapter(private val recipesListViewModel: RecipesListViewModel, private val recipes: List<RecipesItem>) : RecyclerView.Adapter<RecipeViewHolder>() {

    private val onItemClickListener: RecyclerItemListener = object : RecyclerItemListener {
        override fun onItemSelected(recipe: RecipesItem) {
            recipesListViewModel.openRecipeDetails(recipe)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val itemBinding = RecipeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipeViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(recipes[position], onItemClickListener)
    }

    override fun getItemCount(): Int {
        return recipes.size
    }
}

