package com.jatra.receipeappjetpackcompose.presentation.ui.recipe_list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jatra.receipeappjetpackcompose.domain.model.Recipe
import com.jatra.receipeappjetpackcompose.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class RecipeListViewModel
@Inject
constructor(private val recipeRepository: RecipeRepository, @Named("auth_token") private val token: String) : ViewModel() {

    val recipes: MutableState<List<Recipe>> = mutableStateOf(listOf())


    init {
        fetchRecipes()
    }

    private fun fetchRecipes() {
        viewModelScope.launch {
            val result = recipeRepository.search(
                token = token,
                page = 1,
                query = "chicken"
            )
            recipes.value = result
        }
    }

}