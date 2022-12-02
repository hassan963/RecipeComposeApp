package com.jatra.receipeappjetpackcompose.di

import com.jatra.receipeappjetpackcompose.network.model.RecipeDtoMapper
import com.jatra.receipeappjetpackcompose.network.model.RecipeService
import com.jatra.receipeappjetpackcompose.repository.RecipeRepository
import com.jatra.receipeappjetpackcompose.repository.RecipeRepository_Impl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideRecipeRepository(
        recipeService: RecipeService,
        recipeDtoMapper: RecipeDtoMapper
    ): RecipeRepository {
        return RecipeRepository_Impl(
            recipeService,
            recipeDtoMapper
        )
    }
}