package com.jatra.receipeappjetpackcompose.repository

import com.jatra.receipeappjetpackcompose.domain.model.Recipe
import com.jatra.receipeappjetpackcompose.network.model.RecipeDtoMapper
import com.jatra.receipeappjetpackcompose.network.model.RecipeService

class RecipeRepository_Impl(
    private val recipeService: RecipeService,
    private val mapper: RecipeDtoMapper
) : RecipeRepository {
    override suspend fun search(token: String, page: Int, query: String): List<Recipe> {
        val response = recipeService.search(token, page, query).recipes
        return mapper.toDomainList(response)
    }

    override suspend fun get(token: String, id: Int): Recipe {
        val response = recipeService.get(token, id)
        return mapper.mapToDomainModel(response)
    }
}