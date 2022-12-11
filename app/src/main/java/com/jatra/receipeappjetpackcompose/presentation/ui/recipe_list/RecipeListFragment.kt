package com.jatra.receipeappjetpackcompose.presentation.ui.recipe_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.jatra.receipeappjetpackcompose.presentation.BaseApplication
import com.jatra.receipeappjetpackcompose.presentation.ui.components.CircularIndeterminateProgressBar
import com.jatra.receipeappjetpackcompose.presentation.ui.components.RecipeCard
import com.jatra.receipeappjetpackcompose.presentation.ui.components.SearchAppBar
import com.jatra.receipeappjetpackcompose.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RecipeListFragment : Fragment() {

    private val viewModel: RecipeListViewModel by viewModels()

    @Inject
    lateinit var baseApplication: BaseApplication

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                AppTheme(darkTheme = baseApplication.isDark.value) {
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
                            onSelectedCategoryChanged = viewModel::onSelectedCategoryChanged,
                            onToggleTheme = {
                                baseApplication.toggleLightTheme()
                            }
                        )

                        Box(modifier = Modifier.fillMaxSize().background(MaterialTheme.colors.background)) {
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
}