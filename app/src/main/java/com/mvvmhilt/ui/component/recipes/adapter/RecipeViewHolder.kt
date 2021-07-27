package com.mvvmhilt.ui.component.recipes.adapter

import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.mvvmhilt.R
import com.mvvmhilt.data.dto.recipes.RecipesItem
import com.mvvmhilt.databinding.RecipeItemBinding
import com.mvvmhilt.ui.base.listeners.RecyclerItemListener

/**
 * Created by navdeepera04
 */

class RecipeViewHolder(private val itemBinding: RecipeItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(recipesItem: RecipesItem, recyclerItemListener: RecyclerItemListener) {
        itemBinding.tvCaption.text = recipesItem.description
        itemBinding.tvName.text = recipesItem.name
        Picasso.get().load(recipesItem.thumb).placeholder(R.drawable.progress_animation).error(R.drawable.ic_baseline_error_24).into(itemBinding.ivRecipeItemImage)
        itemBinding.rlRecipeItem.setOnClickListener { recyclerItemListener.onItemSelected(recipesItem) }
    }
}

