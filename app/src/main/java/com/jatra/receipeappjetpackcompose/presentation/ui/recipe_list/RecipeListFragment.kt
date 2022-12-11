package com.jatra.receipeappjetpackcompose.presentation.ui.recipe_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.jatra.receipeappjetpackcompose.presentation.ui.components.CircularIndeterminateProgressBar
import com.jatra.receipeappjetpackcompose.presentation.ui.components.RecipeCard
import com.jatra.receipeappjetpackcompose.presentation.ui.components.SearchAppBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipeListFragment : Fragment() {

    private val viewModel: RecipeListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                val recipes = viewModel.recipes.value

                val query = viewModel.query.value

                val selectedCategory = viewModel.selectedCategory.value

                val isLoading = viewModel.isLoading.value

                Column {
                    SearchAppBar(
                        query = query,
                        onQueryChanged = viewModel::onQueryChanged,
                        onExecuteSearch = viewModel::fetchRecipes,
                        selectedCategory = selectedCategory,
                        onSelectedCategoryChanged = viewModel::onSelectedCategoryChanged
                    )

                    Box(modifier = Modifier.fillMaxSize()) {
                        LazyColumn {
                            itemsIndexed(
                                items = recipes
                            ) { _, recipe ->
                                RecipeCard(recipe = recipe, onClick = {})
                            }
                        }

                        CircularIndeterminateProgressBar(isDisplayed = isLoading)
                    }
                }
            }
        }
    }
}