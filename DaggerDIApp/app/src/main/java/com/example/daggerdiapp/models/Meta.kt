package com.example.daggerdiapp.models

import com.squareup.moshi.Json

data class Meta(
    @Json(name="pagination") val pagination: Pagination,
    )