package com.example.restclientretrofitapp.models

import com.squareup.moshi.Json

data class Meta(
    @Json(name="pagination") val pagination:Pagination,

)