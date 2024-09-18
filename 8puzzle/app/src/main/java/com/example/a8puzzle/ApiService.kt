package com.example.a8puzzle

import retrofit2.Call
import retrofit2.http.*


interface ApiService {
    @GET("game?_sort=size,score&_order=asc")
    fun loadPlayerList() : Call<ArrayList<PlayerItemDAO>>

    @POST("game")
    fun createPost(@Body dataModal: PlayerItemDAO): Call<PlayerItemDAO>

}