package com.example.restclientretrofitapp.models

import com.squareup.moshi.Json

data class Link (
    @Json(name="previous") var previous: String? = null ,
    @Json(name="current") var current: String? = null ,
    @Json(name="next")var next: String? = null ,
)
