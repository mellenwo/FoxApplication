package com.jobrapp.server

import kotlinx.coroutines.experimental.Deferred
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

/**
 *
 */
interface ApiService {
    @GET("/users/list")
    fun getDeferredUsers() : Deferred<Response<List<User>>>
    @GET("/users/list")
    fun getUsers() : Call<List<User>>
}