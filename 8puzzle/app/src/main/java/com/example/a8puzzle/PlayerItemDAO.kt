package com.example.a8puzzle

import com.google.gson.annotations.SerializedName

class PlayerItemDAO() {
    @SerializedName("name") var strName: String? = null
    @SerializedName("score") var score: Int? = null
    @SerializedName("size") var size: String? = null
}