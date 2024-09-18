package com.example.a8puzzle

import com.google.gson.annotations.SerializedName

class ItemCollectionDAO {
    @SerializedName("players") var players: ArrayList<PlayerItemDAO>? = null
}