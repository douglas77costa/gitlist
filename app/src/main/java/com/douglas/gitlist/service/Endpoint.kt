package com.douglas.gitlist.service

import com.douglas.gitlist.model.RepositoryEntity
import retrofit2.Call
import retrofit2.http.GET

interface Endpoint {
    @GET("repositories")
    fun getRepositories() : Call<List<RepositoryEntity>>
}