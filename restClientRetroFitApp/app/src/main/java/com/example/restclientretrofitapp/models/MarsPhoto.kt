package com.example.restclientretrofitapp.models

import com.squareup.moshi.Json

data class MarsPhoto(
    @Json(name = "id") val id:String,
    @Json(name = "img_src")val img_src:String

    /**
     * names match with key names from the json objects
     *  we can change the names by using @JSON(name="img_src")  img_src:String
     */


)