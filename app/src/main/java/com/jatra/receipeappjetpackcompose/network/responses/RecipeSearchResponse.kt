package com.jatra.receipeappjetpackcompose.network.responses

import com.google.gson.annotations.SerializedName
import com.jatra.receipeappjetpackcompose.network.model.RecipeDto

data class RecipeSearchResponse(
    @SerializedName("count")
    var count: Int,

    @SerializedName("results")
    var recipes: List<RecipeDto>,
)