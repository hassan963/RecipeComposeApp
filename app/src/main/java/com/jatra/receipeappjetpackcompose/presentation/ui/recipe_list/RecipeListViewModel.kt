package com.jatra.receipeappjetpackcompose.presentation.ui.recipe_list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jatra.receipeappjetpackcompose.domain.model.Recipe
import com.jatra.receipeappjetpackcompose.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class RecipeListViewModel
@Inject
constructor(private val recipeRepository: RecipeRepository, @Named("auth_token") private val token: String) : ViewModel() {

    val recipes: MutableState<List<Recipe>> = mutableStateOf(listOf())

    val query = mutableStateOf("")

    val selectedCategory: MutableState<FoodCategory?> = mutableStateOf(null)

    var categoryScrollPosition: Float = 0f

    val isLoading = mutableStateOf(false)

    init {
        fetchRecipes()
    }

    fun fetchRecipes() {
        viewModelScope.launch {
            isLoading.value = true

            resetSearchList()

            delay(2000)

            val result = recipeRepository.search(
                token = token,
                page = 1,
                query = query.value
            )
            recipes.value = result

            isLoading.value = false
        }
    }

    fun onQueryChanged(query: String) {
        this.query.value = query
    }

    fun onSelectedCategoryChanged(category: String) {
        val newCategory = getFoodCategory(category)
        selectedCategory.value = newCategory
        onQueryChanged(category)
    }

    fun onChangeScrollCategoryPosition(position: Float) {
        categoryScrollPosition = position
    }

    private fun resetSearchList() {
        recipes.value = listOf()

        if (selectedCategory.value?.value != query.value) {
            clearSelectedCategory()
        }
    }

    private fun clearSelectedCategory() {
        selectedCategory.value = null
    }
}